package team.asd.service;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.asd.dao.FeeDao;
import team.asd.entity.Fee;
import team.asd.exception.ValidationException;

import java.util.Date;

@Service
public class FeeService {
	private final FeeDao feeDao;

	public FeeService(@Autowired FeeDao feeDao) {
		this.feeDao = feeDao;
	}

	public Fee readById(Integer id) {
		validId(id);
		return feeDao.readById(id);
	}

	public Fee createFee(Fee fee) {
		validFee(fee, false);
		feeDao.saveFee(fee);
		return feeDao.readById(fee.getId());
	}

	public Fee updateFee(Fee fee) {
		validFee(fee, true);
		feeDao.updateFee(fee);
		return feeDao.readById(fee.getId());
	}

	public void deleteFee(Integer id) {
		validId(id);
		feeDao.deleteFee(id);
	}

	public void validId(Integer id) {
		if (id == null || id < 1) {
			throw new ValidationException("Id is not valid");
		}
	}

	public void validFee(Fee fee, boolean updatable) {
		if (fee == null) {
			throw new ValidationException("Fee is null");
		}
		if (updatable && fee.getId() == null) {
			throw new ValidationException("Id field is null");
		} else if (!updatable && ObjectUtils.anyNull(
				fee.getFeeType(), fee.getProductId(), fee.getState(),
				fee.getFromDate(), fee.getToDate(), fee.getTaxType(),
				fee.getUnit(), fee.getValue(), fee.getValueType(),
				fee.getCurrency()
		)) {
			throw new ValidationException("Required field is null");
		}

		if (!updatable && !fee.getFromDate().before(fee.getToDate())) {
			throw new ValidationException("From_date have to be earlier then To_date");
		} else if (updatable && !ObjectUtils.allNull(fee.getFromDate(), fee.getToDate())) {
			if (fee.getFromDate() == null && !new Date().before(fee.getToDate())) {
				throw new ValidationException("To_date can not be earlier then current");
			} else if (fee.getToDate() == null && !fee.getFromDate().before(new Date())) {
				throw new ValidationException("From_date can not be later then current");
			} else if (!fee.getFromDate().before(fee.getToDate())) {
				throw new ValidationException("From_date have to be earlier then To_date");
			}
		}
	}
}
