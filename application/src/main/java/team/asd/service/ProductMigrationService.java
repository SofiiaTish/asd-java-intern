package team.asd.service;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.asd.client.RedisClient;
import team.asd.dao.FeeDao;
import team.asd.dao.PriceDao;
import team.asd.dao.ProductDao;
import team.asd.entity.Fee;
import team.asd.entity.Price;
import team.asd.entity.Product;
import team.asd.util.ValidationUtil;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductMigrationService {

	private final DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

	private final ProductDao productDao;
	private final PriceDao priceDao;
	private final FeeDao feeDao;
	private final RedisClient redisClient;

	public ProductMigrationService(
			@Autowired ProductDao productDao,
			@Autowired PriceDao priceDao,
			@Autowired FeeDao feeDao,
			@Autowired RedisClient redisClient) {
		this.productDao = productDao;
		this.priceDao = priceDao;
		this.feeDao = feeDao;
		this.redisClient = redisClient;
	}

	public String checkProductMigration(Integer productId, Date fromDate, Date toDate) {
		ValidationUtil.validId(productId);
		ValidationUtil.validDateRange(fromDate, toDate);
		DateFormat.getDateInstance();
		String result = redisClient.readProductMigrationResult(productId, formatter.format(fromDate), formatter.format(toDate));
		return StringUtils.isNotBlank(result) ? result : calculateProductMigrationResult(productId, fromDate, toDate);
	}

	private String calculateProductMigrationResult(Integer productId, Date fromDate, Date toDate) {
		double result = 0.0;
		int product_part = 0;
		int price_part = 0;
		int fee_part = 0;

		Product product = productDao.readById(productId);
		if (product == null || product.getState().toString().equals("Final")) {
			return String.valueOf(result);
		}
		product_part += 2 //supplier_id
				+ (StringUtils.isBlank(product.getName()) ? 0 : (product.getName().length() <= 10 ? 3 : 5))
				+ (product.getState().toString().equals("Created") ? 4
				: (product.getState().toString().equals("Suspended") || product.getState().toString().equals("Initial") ? 3 : 2))
				+ (StringUtils.isNotBlank(product.getCurrency()) ? 4 : 0)
				+ (product.getGuestsNumber() > 0 ? 4 : 0)
				+ (product.getLongitude() != null ? 3 : 0)
				+ (product.getLatitude() != null ? 3 : 0)
				+ (StringUtils.isBlank(product.getPhysicalAddress()) || product.getPhysicalAddress().length() < 2 ? 0
				: (product.getPhysicalAddress().length() <= 4 ? 1 : (product.getPhysicalAddress().length() <= 10 ? 2
				: (product.getPhysicalAddress().length() <= 40 ? 4 : 5))));

		List<Price> prices = priceDao.readPricesByDateRange(productId, fromDate, toDate);
		if (prices != null) {
			prices = prices.stream().filter(price -> price.getState() != null && price.getState().toString().equals("Created")).collect(Collectors.toList());
		}
		if (CollectionUtils.isNotEmpty(prices)) {
			for (Price price : prices) {
				price_part += 3 //entity_type
						+ 4 //entity_id
						+ (StringUtils.isNotBlank(price.getName()) ? 0 : (price.getName().length() <= 10 ? 3 : 7))
						+ 4 //state
						+ 7 //from_date
						+ 7 //to_date
						+ (price.getValue() > 0.0 ? 7 : 0)
						+ (StringUtils.isNotBlank(price.getCurrency()) ? 6 : 0);
			}
			price_part /= prices.size();
		}

		List<Fee> fees = feeDao.readFeesByDateRange(productId, fromDate, toDate);
		if (fees != null) {
			fees = fees.stream().filter(fee -> fee.getState().toString().equals("Created")).collect(Collectors.toList());
		}
		if (CollectionUtils.isNotEmpty(fees)) {
			//fee_part = fees.stream().mapToInt(this::sumFeeResult).sum() / fees.size();
			for (Fee fee : fees) {
				fee_part += 2 //fee_type
						+ 1 //product_id
						+ (fee.getName() == null || fee.getName().length() == 0 ? 0 : 1)
						+ 1 //state
						+ 3 //from_date
						+ 3 //to_date
						+ 2 //tax_type
						+ 3 //unit
						+ (fee.getValue() > 0.0 ? 3 : 0)
						+ 3 //value_type
						+ (StringUtils.isNotBlank(fee.getCurrency()) ? 3 : 0);
			}
			fee_part /= fees.size();
		}

		result = product_part + price_part + fee_part;
		saveProductMigrationResult(productId, fromDate, toDate, result);
		return String.valueOf(result);
	}

	private int sumFeeResult(Fee fee) {
		return 18 + (fee.getName() == null || fee.getName().length() == 0 ? 0 : 1)
				+ (fee.getValue() > 0.0 ? 3 : 0) + (StringUtils.isNotBlank(fee.getCurrency()) ? 3 : 0);
	}

	private void saveProductMigrationResult(Integer productId, Date fromDate, Date toDate, double result) {
		redisClient.saveProductMigrationWithExpire(productId, formatter.format(fromDate), formatter.format(toDate), result);
	}
}
