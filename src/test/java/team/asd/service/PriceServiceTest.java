package team.asd.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import team.asd.constant.PriceState;
import team.asd.dao.PriceDaoTestImpl;
import team.asd.data.PriceDataTest;
import team.asd.entity.Price;
import team.asd.exception.ValidationException;

import java.time.Instant;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class PriceServiceTest {

	private static PriceService priceService;

	private static Price price;

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
	}

	@Test
	void testReadById() {
		assertEquals(PriceDataTest.getExpectedPrice(), priceService.readById(1));
		assertThrows(ValidationException.class, () -> priceService.readById(-1));
		assertThrows(ValidationException.class, () -> priceService.readById(null));
	}

	@Test
	void testCreatePrice() {
		assertThrows(ValidationException.class, () -> priceService.createPrice(null));
		assertThrows(ValidationException.class, () -> priceService.createPrice(new Price()));
		price.setName(null);
		assertThrows(ValidationException.class, () -> priceService.createPrice(price));
	}

	@Test
	void testUpdatePrice() {
		assertThrows(ValidationException.class, () -> priceService.updatePrice(null));
		assertThrows(ValidationException.class, () -> priceService.updatePrice(new Price()));
		price.setId(null);
		price.setCurrency("hrn");
		assertThrows(ValidationException.class, () -> priceService.updatePrice(price));
	}

	@Test
	void testDeletePrice() {
		assertThrows(ValidationException.class, () -> priceService.deletePrice(-1));
		assertThrows(ValidationException.class, () -> priceService.deletePrice(null));
	}
}