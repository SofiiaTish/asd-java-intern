package team.asd.service;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;
import team.asd.dao.ProductDao;
import team.asd.dto.ProductDto;
import team.asd.entity.Product;
import team.asd.exception.ValidationException;
import team.asd.util.ConverterUtil;

@Service
public class ProductService {

    private final ProductDao productDao;

    public ProductService(ProductDao productDao) {
        this.productDao = productDao;
    }

    public ProductDto readById(Integer id) {
        validId(id);
        return ConverterUtil.convertProductToDto(productDao.readById(id));
    }

    public ProductDto createProduct(Product product) {
        validProduct(product, false);
        return ConverterUtil.convertProductToDto(productDao.saveProduct(product));
    }

    public ProductDto updateProduct(Product product) {
        validProduct(product, true);
        return ConverterUtil.convertProductToDto(productDao.updateProduct(product));
    }

    public Integer deleteProduct(Integer id) {
        validId(id);
        return productDao.deleteProduct(id);
    }

    public void validId(Integer id) {
        if (id == null || id < 1) {
            throw new ValidationException("Id is not valid");
        }
    }

    public void validProduct(Product product, boolean updatable) {
        if (product == null) {
            throw new ValidationException("Product is null");
        }
        if (updatable && ObjectUtils.anyNull(product.getId())) {
            throw new ValidationException("Id field is null");
        } else if (ObjectUtils.anyNull(product.getSupplierId(), product.getName(), product.getCurrency())) {
            throw new ValidationException("Required field is null");
        }
    }
}
