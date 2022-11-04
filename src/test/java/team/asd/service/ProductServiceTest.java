package team.asd.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import team.asd.constant.ProductState;
import team.asd.dao.ProductDaoTestImpl;
import team.asd.entity.Product;
import team.asd.exception.ValidationException;

import static org.junit.jupiter.api.Assertions.*;

class ProductServiceTest {

    private static ProductDaoTestImpl productDao;
    private static ProductService productService;
    private Product product;

    @BeforeAll
    public static void setUpClass() {
        productDao = new ProductDaoTestImpl();
        productService = new ProductService(productDao);
    }

    @BeforeEach
    void setUp() {
        product = new Product();
        product.setProductId(1);
        product.setSupplierId(1);
        product.setName("Curr");
        product.setState(ProductState.Created);
        product.setCurrency("usd");
    }

    @Test
    void testReadById() {
        Integer searchId = 2;
        assertNull(productService.readById(null));
        assertNull(productService.readById(-1));
        assertEquals(productDao.getExpectedProduct(), productService.readById(searchId));
    }

    @Test
    void testCreateProduct() {
        assertNull(productService.createProduct(null));
        assertEquals(product, productService.createProduct(product));
        assertThrows(ValidationException.class, () -> productService.createProduct(new Product()));
        product.setName(null);
        assertThrows(ValidationException.class, () -> productService.createProduct(product));
    }

    @Test
    void testUpdateProduct() {
        product.setState(ProductState.Initial);
        assertEquals(product, productService.updateProduct(product));
        assertNull(productService.updateProduct(null));
        product.setProductId(null);
        assertThrows(ValidationException.class, () -> productService.updateProduct(product));
        product.setProductId(1);
        product.setName(null);
        assertThrows(ValidationException.class, () -> productService.updateProduct(product));
    }

    @Test
    void testDeleteProduct() {
        assertNull(productService.deleteProduct(null));
        assertNull(productService.deleteProduct(-1));
        assertEquals(product.getProductId(), productService.deleteProduct(1));
    }
}