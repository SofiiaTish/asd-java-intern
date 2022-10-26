package team.asd.service;

import org.apache.commons.lang3.StringUtils;
import team.asd.constants.DateElement;
import team.asd.exceptions.WrongArgumentException;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;

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
		//!! seconds -> local date time
		return unit.between(fromDate, toDate);
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
		return null;
	}

}
