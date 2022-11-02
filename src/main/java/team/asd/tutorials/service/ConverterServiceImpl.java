package team.asd.tutorials.service;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class ConverterServiceImpl implements IsConverterService {

	@Override
	public String convertIntegerIntoString(Integer value) {
		return value == null ? null : value.toString();
	}

	@Override
	public Integer convertStringIntoInteger(String value) {
		return value == null ? null : Integer.valueOf(value);
	}

	@Override
	public Double convertStringIntoDouble(String value) {
		return value == null ? null : Double.valueOf(value);
	}

	@Override
	public LocalDate convertStringIntoLocalDate(String dateString) throws DateTimeParseException {
		return dateString == null ? null : LocalDate.parse(dateString);
	}
}
