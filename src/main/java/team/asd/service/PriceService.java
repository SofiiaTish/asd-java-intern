package team.asd.service;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.asd.dao.PriceDao;
import team.asd.entity.Price;
import team.asd.exception.ValidationException;
import team.asd.util.ValidationUtil;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class PriceService {

	private final PriceDao priceDao;

	public PriceService(@Autowired PriceDao priceDao) {
		this.priceDao = priceDao;
	}

	public Price readById(Integer id) {
		ValidationUtil.validId(id);
		return priceDao.readById(id);
	}

	public List<Price> readByParams(String entityType, Integer entityId, String state) {
		if (entityId != null && entityId < 1) {
			throw new ValidationException("Incorrect entity Id: not positive");
		}
		return priceDao.readPricesByParams(entityType, entityId, state);
	}

	public List<Price> readByDateRange(Date fromDate, Date toDate) {
		ValidationUtil.validDateRange(fromDate, toDate);
		return priceDao.readPricesByDateRange(fromDate, toDate);
	}

	public List<Price> readPricesByProductMask(String mask) {
		return priceDao.readPricesByProductMask(mask);
	}

	public Price createPrice(Price price) {
		validPrice(price, false);
		priceDao.savePrice(price);
		return priceDao.readById(price.getId());
	}

	public void createPrices(List<Price> prices) {
		CollectionUtils.filter(prices, Objects::nonNull);
		prices.forEach(price -> validPrice(price, false));
		priceDao.savePrices(prices);
	}

	public Price updatePrice(Price price) {
		validPrice(price, true);
		priceDao.updatePrice(price);
		return priceDao.readById(price.getId());
	}

	public void deletePrice(Integer id) {
		ValidationUtil.validId(id);
		priceDao.deletePrice(id);
	}

	private void validPrice(Price price, boolean updatable) {
		if (price == null) {
			throw new ValidationException("Price is null");
		}
		if (updatable) {
			ValidationUtil.validId(price.getId());
		} else {
			ValidationUtil.validFields(price.getEntityType(), price.getEntityId(),
					price.getName(), price.getFromDate(), price.getToDate(),
					price.getValue(), price.getCurrency());
		}

		ValidationUtil.validDates(price.getFromDate(), price.getToDate());
	}

}
