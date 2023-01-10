package team.asd.util;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.util.CollectionUtils;
import team.asd.exception.ValidationException;

import java.util.Collections;
import java.util.Date;
import java.util.List;

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

		if (ObjectUtils.allNull(fromDate, toDate)) {
			return;
		}

		if (fromDate == null && !new Date().before(toDate)) {
			throw new ValidationException("To_date can not be earlier than current");
		}

		if (toDate == null && !new Date().before(fromDate)) {
			throw new ValidationException("From_date can not be earlier than current");
		}
		if (ObjectUtils.anyNull(fromDate, toDate)) {
			return;
		}

		if (!fromDate.before(toDate)) {
			throw new ValidationException("From_date have to be earlier than To_date");
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

	public static void validKey(String key) {
		if (key == null) {
			throw new ValidationException("Incorrect key: null value");
		}
	}

	public static void validExpireTime(Long expireTime) {
		if (expireTime == null || expireTime == 0) {
			throw new ValidationException("Incorrect expire time: null or zero value");
		}
	}

	public static void validValue(String value) {
		if (value == null) {
			throw new ValidationException("Incorrect value: null value");
		}
	}

	public static void validList(List<String> list) {
		if (CollectionUtils.isEmpty(list)) {
			throw new ValidationException("Incorrect value of list: null or empty list");
		}
		if (list.contains(null)) {
			throw new ValidationException("Incorrect value into list: null value");
		}
	}

}
