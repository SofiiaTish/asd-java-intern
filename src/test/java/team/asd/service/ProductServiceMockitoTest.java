package team.asd.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.powermock.reflect.Whitebox;
import team.asd.constant.ProductState;
import team.asd.dao.ProductDao;
import team.asd.entity.Product;
import team.asd.exception.ValidationException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@RunWith(MockitoJUnitRunner.class)
class ProductServiceMockitoTest {
	private ProductService productService;
	@Mock
	private ProductDao productDao;

	private static Product product;
	private AutoCloseable mockClosable;

	@BeforeEach
	void setUp() {
		product = null;
		mockClosable = MockitoAnnotations.openMocks(this);
		productService = new ProductService(productDao);
	}

	@AfterEach
	void tearDown() {
		try {
			mockClosable.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Test
	void testReadById() {
		Mockito.when(productDao.readById(1))
				.thenReturn(Product.builder().id(1).name("Mock").build());
		Product product = productService.readById(1);
		assertNotNull(product);
		assertEquals(1, product.getId());
	}

	@Test
	void testCreateProduct() {
		Exception e = assertThrows(ValidationException.class, () -> Whitebox.invokeMethod(productService, "validProduct", product, false));
		assertEquals("Product is null", e.getMessage());

		product = Product.builder().supplierId(2).build();
		e = assertThrows(ValidationException.class, () -> Whitebox.invokeMethod(productService, "validProduct", product, false));
		assertEquals("Required field is null", e.getMessage());

		Mockito.doAnswer(invocation -> {
			product = Product.builder().id(1).build();
			return null;
		}).when(productDao).saveProduct(Mockito.any(Product.class));
		Mockito.when(productDao.readById(1)).thenAnswer(invocation -> product);

		productService.createProduct(Product.builder()
				.id(1).supplierId(1).name("Mock").state(ProductState.Created).currency("usd").build());

		assertNotNull(productService.readById(1));

		Mockito.verify(productDao).saveProduct(Mockito.any());
		Mockito.verify(productDao, Mockito.times(2)).readById(Mockito.anyInt());
	}

	@Test
	void testUpdateProduct() {
		Mockito.doAnswer(invocation -> {
			product = Product.builder().id(1).build();
			return null;
		}).when(productDao).updateProduct(Mockito.any(Product.class));
		Mockito.when(productDao.readById(1)).thenAnswer(invocation -> product);

		productService.updateProduct(Product.builder()
				.id(1).supplierId(1).name("Mock").state(ProductState.Created).currency("usd").build());
		assertNotNull(productService.readById(1));

		Mockito.verify(productDao).updateProduct(Mockito.any());
		Mockito.verify(productDao, Mockito.times(2)).readById(Mockito.anyInt());

		product.setId(null);
		Exception e = assertThrows(ValidationException.class, () -> Whitebox.invokeMethod(productService, "validProduct", product, true));
		assertEquals("Incorrect Id: null or not positive", e.getMessage());
	}

	@Test
	void testDeleteProduct() {
		productService.deleteProduct(1);
		Mockito.verify(productDao).deleteProduct(Mockito.anyInt());
	}
}