package team.asd.dao;

import org.springframework.stereotype.Repository;
import team.asd.constant.ProductState;
import team.asd.entity.Product;

@Repository
public class ProductDaoImpl implements ProductDao {

    @Override
    public Product readById(Integer id) {
        return getProduct();
    }

    @Override
    public Product saveProduct(Product product) {
        return product;
    }

    @Override
    public Product updateProduct(Product product) {
        return product;
    }

    @Override
    public Integer deleteProduct(Integer id) {
        return id;
    }

    private Product getProduct() {
        Product expectedProduct = new Product();
        expectedProduct.setId(1);
        expectedProduct.setSupplierId(4);
        expectedProduct.setName("Expert");
        expectedProduct.setState(ProductState.Created);
        expectedProduct.setCurrency("USD");
        return expectedProduct;
    }
}
