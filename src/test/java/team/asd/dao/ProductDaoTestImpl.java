package team.asd.dao;

import team.asd.data.ProductDataTest;
import team.asd.entity.Product;

public class ProductDaoTestImpl implements ProductDao {

	@Override
	public Product readById(Integer id) {
		return ProductDataTest.getExpectedProduct();
	}

	@Override
	public void saveProduct(Product product) {
	}

	@Override
	public void updateProduct(Product product) {
	}

	@Override
	public void deleteProduct(Integer id) {
	}
}
