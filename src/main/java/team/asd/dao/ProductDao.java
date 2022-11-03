package team.asd.dao;

import team.asd.entity.Product;

public interface ProductDao {

    Product findById(Integer id);
    Product saveProduct(Product product);
    Product updateProduct(Product product);
    Integer deleteProduct(Product product);
}
