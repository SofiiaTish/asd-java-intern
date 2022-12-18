package team.asd.dao;

import team.asd.entity.ProductReport;
import team.asd.entity.Product;

import java.util.List;

public interface ProductDao {

	Product readById(Integer id);

	List<Product> readProductsByParams(Integer supplierId, String name, String state);

	ProductReport readProductReportById(Integer id);

	void saveProduct(Product product);

	void saveProducts(List<Product> products);

	void updateProduct(Product product);

	void deleteProduct(Integer id);
}
