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
import team.asd.constant.PriceState;
import team.asd.dao.PriceDao;
import team.asd.dao.PriceDaoTestImpl;
import team.asd.data.PriceDataTest;
import team.asd.entity.Price;
import team.asd.exception.ValidationException;

import java.time.Instant;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@RunWith(MockitoJUnitRunner.class)
class PriceServiceTest {
	private static PriceService priceService;

	private PriceService mockPriceService;

	@Mock
	private PriceDao mockPriceDao;

	private static Price price;
	private static Price mockPrice;
	private AutoCloseable mockClosable;

	@BeforeAll
	public static void setUpClass() {
		priceService = new PriceService(new PriceDaoTestImpl());
	}

	@BeforeEach
	void setUp() {
		price = Price.builder()
				.id(1)
				.entityType("Product")
				.entityId(4)
				.name("Test")
				.state(PriceState.Initial)
				.fromDate(Date.from(Instant.now()))
				.toDate(Date.from(Instant.parse("2022-12-15T10:15:30Z")))
				.value(34.56)
				.currency("usd")
				.build();

		mockPrice = null;
		mockClosable = MockitoAnnotations.openMocks(this);
		mockPriceService = new PriceService(mockPriceDao);
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
		assertEquals(PriceDataTest.getExpectedPrice(), priceService.readById(1));
		assertThrows(ValidationException.class, () -> priceService.readById(-1));
		assertThrows(ValidationException.class, () -> priceService.readById(null));

		Mockito.when(mockPriceDao.readById(1))
				.thenReturn(Price.builder().id(1).build());
		Price price = mockPriceService.readById(1);
		assertNotNull(price);
		assertEquals(1, price.getId());
	}

	@Test
	void testCreatePrice() {
		assertThrows(ValidationException.class, () -> priceService.createPrice(null));
		assertThrows(ValidationException.class, () -> priceService.createPrice(new Price()));
		price.setName(null);
		assertThrows(ValidationException.class, () -> priceService.createPrice(price));

		Exception e = assertThrows(ValidationException.class, () -> Whitebox.invokeMethod(mockPriceService, "validPrice", mockPrice, false));
		assertEquals("Price is null", e.getMessage());

		Mockito.doAnswer(invocation -> {
			mockPrice = Price.builder().id(1).build();
			return null;
		}).when(mockPriceDao).savePrice(Mockito.any(Price.class));
		Mockito.when(mockPriceDao.readById(1)).thenAnswer(invocation -> mockPrice);

		assertNull(mockPriceService.readById(1));
		mockPriceService.createPrice(Price.builder()
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
		assertNotNull(mockPriceService.readById(1));

		Mockito.verify(mockPriceDao).savePrice(Mockito.any());
		Mockito.verify(mockPriceDao, Mockito.atLeast(2)).readById(Mockito.anyInt());
	}

	@Test
	void testUpdatePrice() {
		assertThrows(ValidationException.class, () -> priceService.updatePrice(null));
		assertThrows(ValidationException.class, () -> priceService.updatePrice(new Price()));
		price.setId(null);
		price.setCurrency("hrn");
		assertThrows(ValidationException.class, () -> priceService.updatePrice(price));

		Mockito.doAnswer(invocation -> {
			mockPrice = Price.builder().id(1).fromDate(Date.from(Instant.parse("2022-10-15T10:15:30Z"))).build();
			return null;
		}).when(mockPriceDao).updatePrice(Mockito.any(Price.class));
		Mockito.when(mockPriceDao.readById(1)).thenAnswer(invocation -> mockPrice);

		assertNull(mockPriceService.readById(1));
		mockPriceService.updatePrice(Price.builder()
				.id(1).name("Mock").currency("usd").build());
		assertNotNull(mockPriceService.readById(1));

		Mockito.verify(mockPriceDao).updatePrice(Mockito.any());
		Mockito.verify(mockPriceDao, Mockito.atLeast(2)).readById(Mockito.anyInt());

		Exception e = assertThrows(ValidationException.class, () -> Whitebox.invokeMethod(mockPriceService, "validPrice", mockPrice, true));
		assertEquals("From_date can not be earlier than current", e.getMessage());
	}

	@Test
	void testDeletePrice() {
		assertThrows(ValidationException.class, () -> priceService.deletePrice(-1));
		assertThrows(ValidationException.class, () -> priceService.deletePrice(null));

		mockPriceService.deletePrice(1);
		Mockito.verify(mockPriceDao).deletePrice(Mockito.anyInt());
	}
}