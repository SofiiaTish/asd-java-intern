package team.asd.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import team.asd.entity.Product;
import team.asd.mapper.ProductMapper;

import java.util.concurrent.TimeUnit;

@Repository
public class ThreadUpdateDaoImpl implements ThreadUpdateDao {

	private final ProductMapper productMapper;

	public ThreadUpdateDaoImpl(@Autowired ProductMapper productMapper) {
		this.productMapper = productMapper;
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
}
