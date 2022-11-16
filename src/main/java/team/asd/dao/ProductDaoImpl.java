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
	public Product saveProduct(Product product) {
		productMapper.insertProduct(product);
		return productMapper.readProductById(product.getId());
	}

	@Override
	@Transactional
	public Product updateProduct(Product product) {
		productMapper.updateProduct(product);
		return productMapper.readProductById(product.getId());
	}

	@Override
	@Transactional
	public Integer deleteProduct(Integer id) {
		productMapper.deleteProduct(id);
		return id;
	}
}
