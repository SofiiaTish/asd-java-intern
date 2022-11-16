package team.asd.dao;

import team.asd.data.PriceDataTest;
import team.asd.entity.Price;

public class PriceDaoTestImpl implements PriceDao {

	@Override
	public Price readById(Integer id) {
		return PriceDataTest.getExpectedPrice();
	}

	@Override
	public Price saveProduct(Price price) {
		return price;
	}

	@Override
	public Price updateProduct(Price price) {
		return price;
	}

	@Override
	public Integer deleteProduct(Integer id) {
		return id;
	}
}
