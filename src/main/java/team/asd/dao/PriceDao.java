package team.asd.dao;

import team.asd.entity.Price;

public interface PriceDao {

	Price readById(Integer id);

	Price saveProduct(Price price);

	Price updateProduct(Price price);

	Integer deleteProduct(Integer id);
}
