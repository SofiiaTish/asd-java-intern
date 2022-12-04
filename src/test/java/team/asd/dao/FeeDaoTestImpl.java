package team.asd.dao;

import team.asd.data.FeeDataTest;
import team.asd.entity.Fee;

import java.util.List;

public class FeeDaoTestImpl implements FeeDao {

	@Override
	public Fee readById(Integer id) {
		return FeeDataTest.getExpectedFee();
	}

	@Override
	public List<Fee> readFeesByParams(String feeType, Integer productId, String state) {
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
