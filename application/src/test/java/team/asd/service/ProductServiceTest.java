package team.asd.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
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
import team.asd.dao.ProductDaoTestImpl;
import team.asd.data.ProductDataTest;
import team.asd.entity.Product;
import team.asd.exception.ValidationException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@RunWith(MockitoJUnitRunner.class)
class ProductServiceTest {
	private static ProductService productService;
	private ProductService mockProductService;

	@Mock
	private ProductDao mockProductDao;

	private Product product;
	private static Product mockProduct;
	private AutoCloseable mockClosable;

	@BeforeAll
	public static void setUpClass() {
		productService = new ProductService(new ProductDaoTestImpl());
	}

	@BeforeEach
	void setUp() {
		product = Product.builder()
				.id(1)
				.supplierId(1)
				.name("Curr")
				.state(ProductState.Created)
				.currency("usd")
				.build();

		mockProduct = null;
		mockClosable = MockitoAnnotations.openMocks(this);
		mockProductService = new ProductService(mockProductDao);
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
		Integer searchId = 2;
		assertThrows(ValidationException.class, () -> productService.readById(null));
		assertThrows(ValidationException.class, () -> productService.readById(-1));
		assertEquals(ProductDataTest.getExpectedProduct(), productService.readById(searchId));

		Mockito.when(mockProductDao.readById(1))
				.thenReturn(Product.builder().id(1).name("Mock").build());
		Product product = mockProductService.readById(1);
		assertNotNull(product);
		assertEquals(1, product.getId());
	}

	@Test
	void testCreateProduct() {
		assertThrows(ValidationException.class, () -> productService.createProduct(null));
		assertThrows(ValidationException.class, () -> productService.createProduct(new Product()));
		product.setName(null);
		assertThrows(ValidationException.class, () -> productService.createProduct(product));

		Exception e = assertThrows(ValidationException.class, () -> Whitebox.invokeMethod(mockProductService, "validProduct", mockProduct, false));
		assertEquals("Product is null", e.getMessage());

		Mockito.doAnswer(invocation -> {
			mockProduct = Product.builder().id(1).build();
			return null;
		}).when(mockProductDao).saveProduct(Mockito.any(Product.class));
		Mockito.when(mockProductDao.readById(1)).thenAnswer(invocation -> mockProduct);

		assertNull(mockProductService.readById(1));
		mockProductService.createProduct(Product.builder()
				.id(1).supplierId(1).name("Mock").state(ProductState.Created).currency("usd").build());
		assertNotNull(mockProductService.readById(1));

		Mockito.verify(mockProductDao).saveProduct(Mockito.any());
		Mockito.verify(mockProductDao, Mockito.atLeast(2)).readById(Mockito.anyInt());

		mockProduct = Product.builder().supplierId(2).build();
		e = assertThrows(ValidationException.class, () -> Whitebox.invokeMethod(mockProductService, "validProduct", mockProduct, false));
		assertEquals("Required field is null", e.getMessage());
	}

	@Test
	void testUpdateProduct() {
		product.setState(ProductState.Initial);
		assertThrows(ValidationException.class, () -> productService.updateProduct(null));
		product.setId(null);
		assertThrows(ValidationException.class, () -> productService.updateProduct(product));

		Mockito.doAnswer(invocation -> {
			mockProduct = Product.builder().id(1).build();
			return null;
		}).when(mockProductDao).updateProduct(Mockito.any(Product.class));
		Mockito.when(mockProductDao.readById(1)).thenAnswer(invocation -> mockProduct);

		assertNull(mockProductService.readById(1));
		mockProductService.updateProduct(Product.builder()
				.id(1).supplierId(1).name("Mock").state(ProductState.Created).currency("usd").build());
		assertNotNull(mockProductService.readById(1));

		Mockito.verify(mockProductDao).updateProduct(Mockito.any());
		Mockito.verify(mockProductDao, Mockito.atLeast(2)).readById(Mockito.anyInt());

		mockProduct.setId(null);
		Exception e = assertThrows(ValidationException.class, () -> Whitebox.invokeMethod(mockProductService, "validProduct", mockProduct, true));
		assertEquals("Incorrect Id: null or not positive", e.getMessage());
	}

	@Test
	void testDeleteProduct() {
		assertThrows(ValidationException.class, () -> productService.deleteProduct(null));
		assertThrows(ValidationException.class, () -> productService.deleteProduct(-1));

		mockProductService.deleteProduct(1);
		Mockito.verify(mockProductDao).deleteProduct(Mockito.anyInt());
	}
}

