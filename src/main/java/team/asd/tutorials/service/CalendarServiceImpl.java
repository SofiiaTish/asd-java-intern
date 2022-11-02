package team.asd.tutorials.service;

import org.apache.commons.lang3.StringUtils;
import team.asd.tutorials.constants.DateElement;
import team.asd.tutorials.exceptions.WrongArgumentException;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Locale;

public class CalendarServiceImpl implements IsCalendarService {

	private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	@Override
	public String toString(LocalDate date) {
		if (date == null) {
			return null;
		}
		return date.format(formatter);
	}

	@Override
	public LocalDate toLocalDate(String stringDate) {
		if (StringUtils.isEmpty(stringDate)) {
			return null;
		}
		try {
			return LocalDate.parse(stringDate, formatter);
		} catch (DateTimeParseException e) {
			return null;
		}
	}

	@Override
	public long defineCountInRange(LocalDate fromDate, LocalDate toDate, ChronoUnit unit) {
		if (fromDate == null || toDate == null || unit == null) {
			throw new WrongArgumentException("Wrong argument");
		}
		LocalDateTime from = LocalDateTime.of(fromDate, LocalTime.MIN);
		LocalDateTime to = LocalDateTime.of(toDate, LocalTime.MIN);
		return unit.between(from, to);
	}

	@Override
	public String getInfo(LocalDate date, DateElement dateElement) {
		if (date == null || dateElement == null) {
			throw new WrongArgumentException("Wrong argument");
		}
		switch (dateElement) {
		case DAY_OF_WEEK:
			return date.getDayOfWeek()
					.toString();
		case WEEK_NUMBER:
			return String.valueOf((int) Math.ceil((date.get(ChronoField.DAY_OF_YEAR) + LocalDate.ofYearDay(date.getYear(), 1)
					.getDayOfWeek()
					.getValue() - 1) / 7.0));
		case MONTH:
			return date.getMonth()
					.toString();
		case IS_LEAP_YEAR:
			return date.isLeapYear() ? "Yes" : "No";
		}
		return null;
	}

	@Override
	public LocalDate reformatToLocalDate(String dateString) throws DateTimeException {
		if (StringUtils.isEmpty(dateString)) {
			throw new DateTimeException("Wrong date");
		}
		String[] dateParts = dateString.split(" ");
		if (dateParts.length < 3) {
			throw new DateTimeException("Wrong date");
		}
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-MMM-yyyy", Locale.US);
		dateParts[0] = dateParts[0].matches("\\d+\\D{2}") ? dateParts[0].replaceAll("\\D{2}", "") : null;
		int year = dateParts[2].matches("\\d+") ? Integer.parseInt(dateParts[2]) : -1;
		int day = -1;
		if (dateParts[0] != null) {
			day = Integer.parseInt(dateParts[0]);
		}
		if ((day < 1 || day > 31) || (Arrays.stream(Month.values())
				.noneMatch(month -> month.toString()
						.substring(0, 3)
						.equalsIgnoreCase(dateParts[1]))) || (year < 1000 || year > 3000)) {
			throw new DateTimeException("Wrong date");
		}
		dateString = String.join("-", dateParts);
		return LocalDate.parse(dateString, formatter);
	}

}
