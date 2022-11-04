package team.asd.dao;

import team.asd.entity.Product;

public class ProductDaoTestImpl implements ProductDao {

    private Product expectedProduct = new Product();

    public Product getExpectedProduct() {
        expectedProduct.setProductId(2);
        expectedProduct.setSupplierId(4);
        expectedProduct.setName("Exp");
        expectedProduct.setCurrency("usd");
        return expectedProduct;
    }

    @Override
    public Product findById(Integer id) {
        return getExpectedProduct();
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
}
