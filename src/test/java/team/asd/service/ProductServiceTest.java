package team.asd.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import team.asd.constant.ProductState;
import team.asd.dao.ProductDaoTestImpl;
import team.asd.data.ProductDataTest;
import team.asd.entity.Product;
import team.asd.exception.ValidationException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ProductServiceTest {

	private static ProductService productService;
	private Product product;

	@BeforeAll
	public static void setUpClass() {
		productService = new ProductService(new ProductDaoTestImpl());
	}

	@BeforeEach
	void setUp() {
		product = new Product();
		product.setId(1);
		product.setSupplierId(1);
		product.setName("Curr");
		product.setState(ProductState.Created);
		product.setCurrency("usd");
	}

	@Test
	void testReadById() {
		Integer searchId = 2;
		assertThrows(ValidationException.class, () -> productService.readById(null));
		assertThrows(ValidationException.class, () -> productService.readById(-1));
		assertEquals(ProductDataTest.getExpectedProduct(), productService.readById(searchId));
	}

	@Test
	void testCreateProduct() {
		assertThrows(ValidationException.class, () -> productService.createProduct(null));
		assertEquals(product, productService.createProduct(product));
		assertThrows(ValidationException.class, () -> productService.createProduct(new Product()));
		product.setName(null);
		assertThrows(ValidationException.class, () -> productService.createProduct(product));
	}

	@Test
	void testUpdateProduct() {
		product.setState(ProductState.Initial);
		assertEquals(product, productService.updateProduct(product));
		assertThrows(ValidationException.class, () -> productService.updateProduct(null));
		product.setId(null);
		assertThrows(ValidationException.class, () -> productService.updateProduct(product));
		product.setId(1);
		product.setName(null);
		assertEquals(product, productService.updateProduct(product));
	}

	@Test
	void testDeleteProduct() {
		assertThrows(ValidationException.class, () -> productService.deleteProduct(null));
		assertThrows(ValidationException.class, () -> productService.deleteProduct(-1));
		assertEquals(product.getId(), productService.deleteProduct(1));
	}
}

