package team.asd.service;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.asd.dao.PriceDao;
import team.asd.entity.Price;
import team.asd.exception.ValidationException;

@Service
public class PriceService {

	private final PriceDao priceDao;

	public PriceService(@Autowired PriceDao priceDao) {
		this.priceDao = priceDao;
	}

	public Price readById(Integer id) {
		validId(id);
		return priceDao.readById(id);
	}

	public Price createPrice(Price price) {
		validPrice(price, false);
		return priceDao.saveProduct(price);
	}

	public Price updatePrice(Price price) {
		validPrice(price, true);
		return priceDao.updateProduct(price);
	}

	public Integer deletePrice(Integer id) {
		validId(id);
		return priceDao.deleteProduct(id);
	}

	public void validId(Integer id) {
		if (id == null || id < 1) {
			throw new ValidationException("Id is not valid");
		}
	}

	public void validPrice(Price price, boolean updatable) {
		if (price == null) {
			throw new ValidationException("Price is null");
		}
		if (updatable && ObjectUtils.anyNull(price.getId())) {
			throw new ValidationException("Id field is null");
		} else if (!updatable && ObjectUtils.anyNull(
				price.getId(), price.getEntityType(), price.getEntityId(),
				price.getName(), price.getFromDate(), price.getToDate(),
				price.getValue(), price.getCurrency()
		)) {
			throw new ValidationException("Required field is null");
		}
	}

}
