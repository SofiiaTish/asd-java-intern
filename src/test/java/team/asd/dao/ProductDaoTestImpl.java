package team.asd.dao;

import team.asd.data.ProductDataTest;
import team.asd.entity.Product;

import java.util.List;

public class ProductDaoTestImpl implements ProductDao {

	@Override
	public Product readById(Integer id) {
		return ProductDataTest.getExpectedProduct();
	}

	@Override
	public List<Product> readProductsByParams(Integer supplierId, String name, String state) {
		return null;
	}

	@Override
	public void saveProduct(Product product) {
	}

	@Override
	public void saveProducts(List<Product> products) {

	}

	@Override
	public void updateProduct(Product product) {
	}

	@Override
	public void deleteProduct(Integer id) {
	}
}
