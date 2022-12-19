package team.asd.dao;

import team.asd.entity.Price;

import java.util.Date;
import java.util.List;

public interface PriceDao {

	Price readById(Integer id);

	List<Price> readPricesByParams(String entityType, Integer entityId, String state);

	List<Price> readPricesByDateRange(Date fromDate, Date toDate);

	List<Price> readPricesByProductMask(String mask);

	void savePrice(Price price);

	void savePrices(List<Price> prices);

	void updatePrice(Price price);

	void deletePrice(Integer id);
}
