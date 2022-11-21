package team.asd.dao;

import team.asd.entity.Price;

public interface PriceDao {

	Price readById(Integer id);

	void savePrice(Price price);

	void updatePrice(Price price);

	void deletePrice(Integer id);
}
