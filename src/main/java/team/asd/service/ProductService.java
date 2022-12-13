package team.asd.service;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.asd.dao.ProductDao;
import team.asd.dto.ProductReport;
import team.asd.entity.Product;
import team.asd.exception.ValidationException;
import team.asd.util.ValidationUtil;

import java.util.List;
import java.util.Objects;

@Service
public class ProductService {

	private final ProductDao productDao;

	public ProductService(@Autowired ProductDao productDao) {
		this.productDao = productDao;
	}

	public Product readById(Integer id) {
		ValidationUtil.validId(id);
		return productDao.readById(id);
	}

	public List<Product> readByParams(Integer supplierId, String name, String state) {
		if (supplierId != null && supplierId < 1) {
			throw new ValidationException("Incorrect Id: not positive");
		}
		return productDao.readProductsByParams(supplierId, name, state);
	}

	public ProductReport readProductReportById(Integer id){
		ValidationUtil.validId(id);
		return productDao.readProductReportById(id);
	}

	public Product createProduct(Product product) {
		validProduct(product, false);
		productDao.saveProduct(product);
		return productDao.readById(product.getId());
	}

	public void createProducts(List<Product> products) {
		CollectionUtils.filter(products, Objects::nonNull);
		products.forEach(product -> validProduct(product, false));
		productDao.saveProducts(products);
	}

	public Product updateProduct(Product product) {
		validProduct(product, true);
		productDao.updateProduct(product);
		return productDao.readById(product.getId());
	}

	public void deleteProduct(Integer id) {
		ValidationUtil.validId(id);
		productDao.deleteProduct(id);
	}

	private void validProduct(Product product, boolean updatable) {
		if (product == null) {
			throw new ValidationException("Product is null");
		}
		if (updatable) {
			ValidationUtil.validId(product.getId());
		} else {
			ValidationUtil.validFields(product.getSupplierId(), product.getName(), product.getCurrency());
		}
	}
}
