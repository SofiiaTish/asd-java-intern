package team.asd.dao;

import org.springframework.stereotype.Repository;
import team.asd.constant.PriceState;
import team.asd.entity.Price;

import java.time.Instant;
import java.util.Date;

@Repository
public class PriceDaoImpl implements PriceDao {

	private final Price expectedPrice = getExpectedPrice();

	@Override
	public Price readById(Integer id) {
		return expectedPrice;
	}

	@Override
	public Price savePrice(Price price) {
		return price;
	}

	@Override
	public Price updatePrice(Price price) {
		return price;
	}

	@Override
	public Integer deletePrice(Integer id) {
		return id;
	}

	private Price getExpectedPrice() {
		return Price.builder()
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
}
