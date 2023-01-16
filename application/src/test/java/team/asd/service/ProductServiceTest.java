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
import team.asd.dao.PartyDao;
import team.asd.dao.ProductDao;
import team.asd.dao.ReservationDao;
import team.asd.entity.Product;
import team.asd.exception.ValidationException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@RunWith(MockitoJUnitRunner.class)
class ProductServiceTest {
	private ProductService mockProductService;

	@Mock
	private ProductDao mockProductDao;

	private static PartyDao partyDao;

	private static ReservationDao reservationDao;

	private Product product;
	private static Product mockProduct;
	private AutoCloseable mockClosable;

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
		mockProductService = new ProductService(mockProductDao, partyDao, reservationDao);
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
		//Integer searchId = 2;
		assertThrows(ValidationException.class, () -> mockProductService.readById(null));
		assertThrows(ValidationException.class, () -> mockProductService.readById(-1));
		//assertEquals(ProductDataTest.getExpectedProduct(), mockProductService.readById(searchId));

		Mockito.when(mockProductDao.readById(1))
				.thenReturn(Product.builder().id(1).name("Mock").build());
		Product product = mockProductService.readById(1);
		assertNotNull(product);
		assertEquals(1, product.getId());
	}

	@Test
	void testCreateProduct() {
		assertThrows(ValidationException.class, () -> mockProductService.createProduct(null));
		assertThrows(ValidationException.class, () -> mockProductService.createProduct(new Product()));
		product.setName(null);
		assertThrows(ValidationException.class, () -> mockProductService.createProduct(product));

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
		assertThrows(ValidationException.class, () -> mockProductService.updateProduct(null));
		product.setId(null);
		assertThrows(ValidationException.class, () -> mockProductService.updateProduct(product));

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
		assertThrows(ValidationException.class, () -> mockProductService.deleteProduct(null));
		assertThrows(ValidationException.class, () -> mockProductService.deleteProduct(-1));

		mockProductService.deleteProduct(1);
		Mockito.verify(mockProductDao).deleteProduct(Mockito.anyInt());
	}
}

