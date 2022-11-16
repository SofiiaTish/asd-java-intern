package team.asd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.asd.dao.PriceDao;
import team.asd.entity.Price;

@Service
public class PriceService {

	private final PriceDao priceDao;

	public PriceService(@Autowired PriceDao priceDao) {
		this.priceDao = priceDao;
	}

	public Price readById(Integer id) {
		return priceDao.readById(id);
	}

	public Price createPrice(Price price) {
		return priceDao.saveProduct(price);
	}

	public Price updatePrice(Price price) {
		return priceDao.updateProduct(price);
	}

	public Integer deletePrice(Integer id) {
		return priceDao.deleteProduct(id);
	}
}
