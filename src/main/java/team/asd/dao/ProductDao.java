package team.asd.dao;

import team.asd.entity.Product;

public interface ProductDao {

	Product readById(Integer id);

	Product saveProduct(Product product);

	Product updateProduct(Product product);

	Integer deleteProduct(Integer id);
}
