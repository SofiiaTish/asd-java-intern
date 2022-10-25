package team.asd.service;

import lombok.NonNull;
import org.apache.commons.collections4.CollectionUtils;
import team.asd.entities.IsPerDayPrice;
import team.asd.entities.IsPrice;
import team.asd.exceptions.WrongPriceException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Period;
import java.util.List;
import java.util.function.Function;

public class PriceServiceImpl implements IsPriceService {

	@Override
	@NonNull
	public BigDecimal defineAverageValueFromPerDayPrice(List<IsPerDayPrice> prices) throws WrongPriceException {
		if (CollectionUtils.isEmpty(prices)) {
			return BigDecimal.ZERO;
		}

		if ((int) prices.stream()
				.map(IsPerDayPrice::getDate)
				.distinct()
				.count() != prices.size()) {
			throw new WrongPriceException("Two equal dates");
		}

		if (prices.stream()
				.anyMatch(p -> p.getDate() == null) || prices.stream()
				.anyMatch(p -> p.getPrice() == null)) {
			throw new WrongPriceException("Null date or price in item");
		}

		return prices.stream()
				.map(IsPerDayPrice::getPrice)
				.reduce(BigDecimal.ZERO, BigDecimal::add)
				.divide(BigDecimal.valueOf(prices.size()), RoundingMode.HALF_UP);
	}

	@Override
	@NonNull
	public BigDecimal defineAverageValueFromPrices(List<IsPrice> prices) throws WrongPriceException {
		if (CollectionUtils.isEmpty(prices)) {
			return BigDecimal.ZERO;
		}
		if (prices.contains(null) || prices.stream()
				.anyMatch(p -> p.getFromDate() == null || p.getToDate() == null)) {
			throw new WrongPriceException("Null price or date");
		}

		if (prices.stream()
				.anyMatch(price -> prices.stream()
						.anyMatch(nextPrice -> (nextPrice.getFromDate()
								.isAfter(price.getFromDate()) && nextPrice.getFromDate()
								.isBefore(price.getToDate())) || (nextPrice.getToDate()
								.isAfter(price.getFromDate()) && nextPrice.getToDate()
								.isBefore(price.getToDate()))))) {
			throw new WrongPriceException("Dates collides");
		}

		return getAverageData(prices, true).divide(getAverageData(prices, false), RoundingMode.HALF_DOWN);
	}

	private BigDecimal getAverageData(List<IsPrice> list, boolean isPredicate) {
		return list.stream()
				.map(isPredicate ? getPricePredicate() : this::getDuration)
				.reduce(BigDecimal.ZERO, BigDecimal::add);
	}

	@NonNull
	private Function<IsPrice, BigDecimal> getPricePredicate() {
		return p -> p.getPrice()
				.multiply(getDuration(p));
	}

	private BigDecimal getDuration(IsPrice price) {
		return BigDecimal.valueOf(Period.between(price.getFromDate(), price.getToDate())
				.getDays() + 1);
	}

}
