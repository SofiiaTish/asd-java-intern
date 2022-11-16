package team.asd.dao;

import team.asd.entity.Price;

public interface PriceDao {

	Price readById(Integer id);

	Price saveProduct(Price product);

	Price updateProduct(Price product);

	Integer deleteProduct(Integer id);
}
