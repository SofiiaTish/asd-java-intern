package team.asd.dao;

import team.asd.entity.Price;

public interface PriceDao {

	Price readById(Integer id);

	Price savePrice(Price price);

	Price updatePrice(Price price);

	Integer deletePrice(Integer id);
}
