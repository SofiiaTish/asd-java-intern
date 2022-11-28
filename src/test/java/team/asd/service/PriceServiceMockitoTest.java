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
import team.asd.constant.PriceState;
import team.asd.dao.PriceDao;
import team.asd.entity.Price;
import team.asd.exception.ValidationException;

import java.time.Instant;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@RunWith(MockitoJUnitRunner.class)
class PriceServiceMockitoTest {
	private PriceService priceService;
	@Mock
	private PriceDao priceDao;

	private static Price price;
	private AutoCloseable mockClosable;

	@BeforeEach
	void setUp() {
		price = null;
		mockClosable = MockitoAnnotations.openMocks(this);
		priceService = new PriceService(priceDao);
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
		Mockito.when(priceDao.readById(1))
				.thenReturn(Price.builder().id(1).build());
		Price price = priceService.readById(1);
		assertNotNull(price);
		assertEquals(1, price.getId());
	}


	@Test
	void testCreatePrice() {
		Exception e = assertThrows(ValidationException.class, () -> Whitebox.invokeMethod(priceService, "validPrice", price, false));
		assertEquals("Price is null", e.getMessage());

		Mockito.doAnswer(invocation -> {
			price = Price.builder().id(1).build();
			return null;
		}).when(priceDao).savePrice(Mockito.any(Price.class));
		Mockito.when(priceDao.readById(1)).thenAnswer(invocation -> price);

		priceService.createPrice(Price.builder()
				.id(1)
				.entityType("Product")
				.entityId(4)
				.name("Test")
				.state(PriceState.Initial)
				.fromDate(Date.from(Instant.parse("2022-12-10T10:15:30Z")))
				.toDate(Date.from(Instant.parse("2022-12-15T10:15:30Z")))
				.value(34.56)
				.currency("usd")
				.build());
		assertNotNull(priceService.readById(1));

		Mockito.verify(priceDao).savePrice(Mockito.any());
		Mockito.verify(priceDao, Mockito.times(2)).readById(Mockito.anyInt());
	}

	@Test
	void testUpdatePrice() {
		Mockito.doAnswer(invocation -> {
			price = Price.builder().id(1).fromDate(Date.from(Instant.parse("2022-10-15T10:15:30Z"))).build();
			return null;
		}).when(priceDao).updatePrice(Mockito.any(Price.class));
		Mockito.when(priceDao.readById(1)).thenAnswer(invocation -> price);

		priceService.updatePrice(Price.builder()
				.id(1).name("Mock").currency("usd").build());
		assertNotNull(priceService.readById(1));

		Mockito.verify(priceDao).updatePrice(Mockito.any());
		Mockito.verify(priceDao, Mockito.times(2)).readById(Mockito.anyInt());

		Exception e = assertThrows(ValidationException.class, () -> Whitebox.invokeMethod(priceService, "validPrice", price, true));
		assertEquals("From_date can not be earlier then current", e.getMessage());
	}

	@Test
	void testDeletePrice() {
		priceService.deletePrice(1);
		Mockito.verify(priceDao).deletePrice(Mockito.anyInt());
	}
}