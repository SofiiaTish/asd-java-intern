package team.asd.dao;

import team.asd.entity.Price;

import java.util.List;

public interface PriceDao {

	Price readById(Integer id);

	List<Price> readPricesByParams(String entityType, Integer entityId, String state);

	void savePrice(Price price);

	void savePrices(List<Price> prices);

	void updatePrice(Price price);

	void deletePrice(Integer id);
}
