package team.asd.dao;

import team.asd.entity.Product;

public interface ProductDao {

	Product readById(Integer id);

	void saveProduct(Product product);

	void updateProduct(Product product);

	void deleteProduct(Integer id);
}
