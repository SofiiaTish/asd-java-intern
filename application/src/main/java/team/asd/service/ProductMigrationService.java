package team.asd.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.asd.client.RedisClient;
import team.asd.dao.FeeDao;
import team.asd.dao.PriceDao;
import team.asd.dao.ProductDao;
import team.asd.entity.Product;
import team.asd.util.ValidationUtil;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

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

		product_part = (product.getSupplierId() != null ? 2 : 0)
				+ (StringUtils.isBlank(product.getName()) ? 0 : (product.getName().length() <= 10 ? 3 : 5))
				+ (product.getState().toString().equals("Created") ? 4
				: (product.getState().toString().equals("Suspended") || product.getState().toString().equals("Initial") ? 3 : 2))
				+ (product.getCurrency().length() > 0 ? 4 : 0)
				+ (product.getGuestsNumber() > 0 ? 4 : 0)
				+ (product.getLongitude() != null ? 3 : 0)
				+ (product.getLatitude() != null ? 3 : 0)
				+ (StringUtils.isBlank(product.getPhysicalAddress()) || product.getPhysicalAddress().length() < 2 ? 0
				: (product.getPhysicalAddress().length() <= 4 ? 1 : (product.getPhysicalAddress().length() <= 10 ? 2
				: (product.getPhysicalAddress().length() <= 40 ? 4 : 5))));

		saveProductMigrationResult(productId, fromDate, toDate, result);
		return String.valueOf(result);
	}

	private void saveProductMigrationResult(Integer productId, Date fromDate, Date toDate, double result) {
		redisClient.saveProductMigrationWithExpire(productId, formatter.format(fromDate), formatter.format(toDate), result);
	}
}
