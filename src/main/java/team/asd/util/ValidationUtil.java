package team.asd.util;

import org.apache.commons.lang3.ObjectUtils;
import team.asd.exception.ValidationException;

import java.util.Date;

public class ValidationUtil {
	public static void validId(Integer id) {
		if (id == null || id < 1) {
			throw new ValidationException("Incorrect Id: null or not positive");
		}
	}

	public static void validFields(Object... objects) {
		if (ObjectUtils.anyNull(objects)) {
			throw new ValidationException("Required field is null");
		}
	}

	public static void validDates(Date fromDate, Date toDate) {
		if (!ObjectUtils.allNull(fromDate, toDate)) {
			if (fromDate == null && !new Date().before(toDate)) {
				throw new ValidationException("To_date can not be earlier than current");
			} else if (toDate == null && !new Date().before(fromDate)) {
				throw new ValidationException("From_date can not be earlier than current");
			} else if (!ObjectUtils.anyNull(fromDate, toDate)) {
				if (!fromDate.before(toDate)) {
					throw new ValidationException("From_date have to be earlier than To_date");
				}
			}
		}
	}

	public static void validDateRange(Date fromDate, Date toDate) {
		if (ObjectUtils.anyNull(fromDate, toDate)) {
			throw new ValidationException("Date is null");
		}
		if (!fromDate.before(toDate)) {
			throw new ValidationException("From_date have to be earlier then To_date");
		}
	}
}
