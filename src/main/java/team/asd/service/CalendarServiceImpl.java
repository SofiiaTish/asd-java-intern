package team.asd.service;

import org.apache.commons.lang3.StringUtils;
import team.asd.constants.DateElement;
import team.asd.exceptions.WrongArgumentException;

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
		LocalDate localDate;
		try {
			localDate = LocalDate.parse(stringDate, formatter);
		} catch (DateTimeParseException e) {
			return null;
		}
		return localDate;
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
			return String.valueOf(date.get(ChronoField.ALIGNED_WEEK_OF_MONTH));
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
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dMMMyyyy");
		//0
		dateParts[0] = dateParts[0].matches("\\d+[A-Za-z]+") ? dateParts[0].replace("[A-Za-z]+", "") : null;
		int day;
		if (dateParts[0] != null) {
			day = Integer.parseInt(dateParts[0]);
			if (day < 1 || day > 31) {
				throw new DateTimeException("Wrong date");
			}
		}
		//1
		if (Arrays.stream(Month.values())
				.noneMatch(month -> month.toString()
						.substring(0, 3)
						.equalsIgnoreCase(dateParts[1]))) {
			throw new DateTimeException("Wrong date");
		}
		//2
		int year = dateParts[2].matches("\\d+") ? Integer.parseInt(dateParts[2]) : -1;
		if (year < 1000 || year > 3000) {
			throw new DateTimeException("Wrong date");
		}
		dateString = String.join("", dateParts);
		return LocalDate.parse(dateString, formatter);
	}

}
