package team.asd.data;

import team.asd.constant.PriceState;
import team.asd.entity.Price;

import java.time.Instant;
import java.util.Date;

public class PriceDataTest {
	private static Price expectedPrice;

	public static Price getExpectedPrice() {
		if (expectedPrice == null) {
			Price.builder()
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
		return expectedPrice;
	}
}
