package team.asd.service;

import team.asd.dao.ProductDao;
import team.asd.entity.Product;

public class ProductService {

    private ProductDao productDao;

    public Product readById(Integer id) {
        return productDao.findById(id);
    }

    public Product createProduct(Product product) {
        return productDao.saveProduct(product);
    }

    public Product updateProduct(Product product) {
        return productDao.updateProduct(product);
    }

    public Integer deleteProduct(Product product) {
        return productDao.deleteProduct(product);
    }
}
