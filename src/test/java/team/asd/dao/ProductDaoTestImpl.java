package team.asd.dao;

import team.asd.data.ProductDataTest;
import team.asd.entity.Product;

public class ProductDaoTestImpl implements ProductDao {

	@Override
	public Product readById(Integer id) {
		return ProductDataTest.getExpectedProduct();
	}

	@Override
	public Product saveProduct(Product product) {
		return null;
	}

	@Override
	public Product updateProduct(Product product) {
		return null;
	}

	@Override
	public Integer deleteProduct(Integer id) {
		return null;
	}
}
