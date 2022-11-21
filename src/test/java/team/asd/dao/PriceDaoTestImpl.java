package team.asd.dao;

import team.asd.data.PriceDataTest;
import team.asd.entity.Price;

public class PriceDaoTestImpl implements PriceDao {

	@Override
	public Price readById(Integer id) {
		return PriceDataTest.getExpectedPrice();
	}

	@Override
	public void savePrice(Price price) {
	}

	@Override
	public void updatePrice(Price price) {
	}

	@Override
	public void deletePrice(Integer id) {
	}
}
