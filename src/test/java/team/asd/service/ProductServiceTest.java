package team.asd.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import team.asd.dao.ProductDao;
import team.asd.dao.ProductDaoTestImpl;

import static org.junit.jupiter.api.Assertions.*;

class ProductServiceTest {

    private ProductDao productDao;

    @BeforeEach
    void setUp() {
        productDao = new ProductDaoTestImpl();
    }

    @Test
    void readById() {
    }

    @Test
    void createProduct() {
    }

    @Test
    void updateProduct() {
    }

    @Test
    void deleteProduct() {
    }
}