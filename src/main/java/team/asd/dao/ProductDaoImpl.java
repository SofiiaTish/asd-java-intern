package team.asd.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import team.asd.entity.Product;
import team.asd.mapper.ProductMapper;

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
	public void saveProduct(Product product) {
		productMapper.insertProduct(product);
	}

	@Override
	@Transactional
	public void updateProduct(Product product) {
		productMapper.updateProduct(product);
	}

	@Override
	@Transactional
	public void deleteProduct(Integer id) {
		productMapper.deleteProduct(id);
	}
}
