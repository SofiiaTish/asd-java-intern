package team.asd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.asd.dao.FeeDao;
import team.asd.entity.Fee;
import team.asd.exception.ValidationException;
import team.asd.util.ValidationUtil;

@Service
public class FeeService {
	private final FeeDao feeDao;

	public FeeService(@Autowired FeeDao feeDao) {
		this.feeDao = feeDao;
	}

	public Fee readById(Integer id) {
		ValidationUtil.validId(id);
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
		ValidationUtil.validId(id);
		feeDao.deleteFee(id);
	}

	public void validFee(Fee fee, boolean updatable) {
		if (fee == null) {
			throw new ValidationException("Fee is null");
		}
		if (updatable) {
			ValidationUtil.validId(fee.getId());
		} else {
			ValidationUtil.validFields(fee.getFeeType(), fee.getProductId(), fee.getState(),
					fee.getFromDate(), fee.getToDate(), fee.getTaxType(),
					fee.getUnit(), fee.getValue(), fee.getValueType(),
					fee.getCurrency());
		}
		ValidationUtil.validDates(fee.getFromDate(), fee.getToDate());
	}
}
