package team.asd.tutorials.service;

import lombok.NonNull;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamApiServiceImpl implements IsStreamApiService {

	@Override
	@NonNull
	public Stream<?> getNonNullStreamItems(Collection<?> collection) {
		if (CollectionUtils.isEmpty(collection)) {
			return Stream.empty();
		}
		return collection.stream()
				.filter(Objects::nonNull);
	}

	@Override
	@NonNull
	public List<Integer> defineListFromRange(Integer start, Integer end) throws NumberFormatException {
		if (start == null || end == null) {
			return Collections.emptyList();
		}
		if (start > end) {
			Integer temp = start;
			start = end;
			end = temp;
		}

		return IntStream.rangeClosed(start, end)
				.boxed()
				.collect(Collectors.toList());
	}

	@Override
	@NonNull
	public List<Integer> convertStringListToIntegerList(List<String> stringList) {
		if (CollectionUtils.isEmpty(stringList)) {
			return Collections.emptyList();
		}
		return stringList.stream()
				.map(this::checkStringNumber)
				.filter(Objects::nonNull)
				.collect(Collectors.toList());
	}

	private Integer checkStringNumber(String string) {
		try {
			return Integer.valueOf(string);
		} catch (NumberFormatException e) {
			return null;
		}
	}

	@Override
	@NonNull
	public IntStream convertStringToLegalChars(String value) {
		if (StringUtils.isEmpty(value)) {
			return IntStream.empty();
		}
		return value.chars()
				.filter(c -> (c >= 'A' && c <= 'z') || (c >= '0' && c <= '9'));
	}

	@Override
	@NonNull
	public BigDecimal sumAllValues(List<BigDecimal> values) {
		if (values == null) {
			throw new NullPointerException();
		}
		if (CollectionUtils.isEmpty(values) || values.stream()
				.allMatch(Objects::isNull)) {
			return BigDecimal.ZERO;
		}
		return values.stream()
				.filter(Objects::nonNull)
				.reduce(BigDecimal.ZERO, BigDecimal::add);
	}

	@Override
	@NonNull
	public Stream<LocalDate> sortLocalDateList(List<LocalDate> listOfDates) {
		if (CollectionUtils.isEmpty(listOfDates)) {
			return Stream.empty();
		}
		return listOfDates.stream()
				.filter(Objects::nonNull)
				.sorted();
	}

	@Override
	@NonNull
	public Stream<LocalDate> skipDaysFromSpecifiedDate(List<LocalDate> listOfDates, LocalDate date, Integer daysToSkip) {
		if (CollectionUtils.isEmpty(listOfDates) || date == null || daysToSkip == null || daysToSkip < 0) {
			return Stream.empty();
		}
		listOfDates = listOfDates.stream()
				.filter(Objects::nonNull)
				.sorted()
				.filter(current -> current.isAfter(date))
				.skip(daysToSkip)
				.collect(Collectors.toList());
		return Stream.concat(Stream.of(date), listOfDates.stream());
	}

	@Override
	@NonNull
	public List<? extends Object> collectLists(List<?>... lists) {
		if (lists.length == 0) {
			return Collections.emptyList();
		}
		return Stream.of(lists)
				.flatMap(Collection::stream)
				.collect(Collectors.toList());
	}

	@Override
	@NonNull
	public List<? extends Object> removeDuplicates(List<?> listWithDuplicates) {
		if (CollectionUtils.isEmpty(listWithDuplicates)) {
			return Collections.emptyList();
		}
		return listWithDuplicates.stream()
				.distinct()
				.collect(Collectors.toList());
	}
}
