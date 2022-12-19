package team.asd.dao;

import team.asd.data.FeeDataTest;
import team.asd.entity.Fee;

import java.util.Date;
import java.util.List;

public class FeeDaoTestImpl implements FeeDao {

	@Override
	public Fee readById(Integer id) {
		return FeeDataTest.getExpectedFee();
	}

	@Override
	public List<Fee> readFeesByParams(Integer feeType, Integer productId, String state) {
		return null;
	}

	@Override
	public List<Fee> readFeesByDateRange(Date fromDate, Date toDate) {
		return null;
	}

	@Override
	public List<Fee> readFeesByValueProductSupplierId(Integer minValue, Integer supplierId) {
		return null;
	}

	@Override
	public void saveFee(Fee fee) {
	}

	@Override
	public void saveFees(List<Fee> fees) {

	}

	@Override
	public void updateFee(Fee fee) {
	}

	@Override
	public void deleteFee(Integer id) {
	}
}
