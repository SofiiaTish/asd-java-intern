package team.asd.service;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;
import team.asd.dao.ProductDao;
import team.asd.entity.Product;
import team.asd.exception.ValidationException;

@Service
public class ProductService {

    private final ProductDao productDao;

    public ProductService(ProductDao productDao) {
        this.productDao = productDao;
    }

    public Product readById(Integer id) {
        if (id == null || id < 1) {
            return null;
        }
        return productDao.findById(id);
    }

    public Product createProduct(Product product) {
        if (product == null) {
            return null;
        }
        if (ObjectUtils.anyNull(product.getSupplierId(), product.getName(), product.getCurrency())) {
            throw new ValidationException("Required field is null");
        }
        return productDao.saveProduct(product);
    }

    public Product updateProduct(Product product) {
        if (product == null) {
            return null;
        }
        if (ObjectUtils.anyNull(product.getProductId(), product.getSupplierId(), product.getName(), product.getCurrency())) {
            throw new ValidationException("Required field is null");
        }
        return productDao.updateProduct(product);
    }

    public Integer deleteProduct(Integer id) {
        if (id == null || id < 1) {
            return null;
        }
        return productDao.deleteProduct(id);
    }
}
