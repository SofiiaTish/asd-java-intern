package team.asd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.asd.dao.PriceDao;
import team.asd.entity.Price;
import team.asd.exception.ValidationException;
import team.asd.util.ValidationUtil;

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

	public Price createPrice(Price price) {
		validPrice(price, false);
		priceDao.savePrice(price);
		return priceDao.readById(price.getId());
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

	public void validPrice(Price price, boolean updatable) {
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
