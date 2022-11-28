package team.asd.service;

import org.junit.jupiter.api.Assertions;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@RunWith(MockitoJUnitRunner.class)
class ProductServiceMockitoTest {

	private ProductService productService;
	@Mock
	private ProductDao productDao;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		productService = new ProductService(productDao);
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
		final Product[] product = new Product[1];
		Mockito.doAnswer(invocation -> {
			product[0] = Product.builder().id(1).build();
			return null;
		}).when(productDao).saveProduct(Mockito.any(Product.class));
		Mockito.when(productDao.readById(1)).thenReturn(product[0]);

		//Mockito.verify(productDao).saveProduct(Mockito.any());
		//Mockito.verify(productDao).readById(Mockito.anyInt());

		assertNotNull(productService.createProduct(Product.builder()
				.id(1).supplierId(1).name("Mock").state(ProductState.Created).currency("usd").build()));
	}

	@Test
	void updateProduct() {
	}

	@Test
	void deleteProduct() {
	}
}