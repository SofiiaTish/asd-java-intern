package team.asd.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import team.asd.dto.ProductReport;
import team.asd.entity.Product;
import team.asd.mapper.ProductMapper;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Repository
public class ProductDaoImpl implements ProductDao {

	private final ProductMapper productMapper;

	public ProductDaoImpl(@Autowired ProductMapper productMapper) {
		this.productMapper = productMapper;
	}

	@Override
	@Transactional
	public Product readById(Integer id) {
		return productMapper.readProductById(id);
	}

	@Override
	@Transactional
	public List<Product> readProductsByParams(Integer supplierId, String name, String state) {
		return productMapper.readProductsByParams(supplierId, name, state);
	}

	@Override
	@Transactional
	public ProductReport readProductReportById(Integer id) {
		ProductReport productReport = productMapper.readProductReportById(id);
		return productReport == null ? productMapper.readProductReportByIdWithoutLists(id) : productReport;
	}

	@Override
	@Transactional
	public void saveProduct(Product product) {
		productMapper.insertProduct(product);
	}

	@Override
	@Transactional
	public void saveProducts(List<Product> products) {
		productMapper.insertProducts(products);
	}

	@Override
	@Transactional
	public void updateProduct(Product product) {
		productMapper.updateProduct(product);
	}

	@Override
	@Transactional
	public void threadUpdateProduct(Product product) {
		try {
			TimeUnit.SECONDS.sleep(15);
			productMapper.updateProduct(product);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	@Transactional
	public void deleteProduct(Integer id) {
		productMapper.deleteProduct(id);
	}
}
