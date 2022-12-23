package team.asd.dao;

import team.asd.data.PriceDataTest;
import team.asd.entity.Price;

import java.util.Date;
import java.util.List;

public class PriceDaoTestImpl implements PriceDao {

	@Override
	public Price readById(Integer id) {
		return PriceDataTest.getExpectedPrice();
	}

	@Override
	public List<Price> readPricesByParams(String entityType, Integer entityId, String state) {
		return null;
	}

	@Override
	public List<Price> readPricesByDateRange(Date fromDate, Date toDate) {
		return null;
	}

	@Override
	public List<Price> readPricesByProductMask(String mask) {
		return null;
	}

	@Override
	public void savePrice(Price price) {
	}

	@Override
	public void savePrices(List<Price> prices) {

	}

	@Override
	public void updatePrice(Price price) {
	}

	@Override
	public void deletePrice(Integer id) {
	}
}
