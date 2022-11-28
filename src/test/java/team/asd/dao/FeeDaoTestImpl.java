package team.asd.dao;

import team.asd.data.FeeDataTest;
import team.asd.entity.Fee;

public class FeeDaoTestImpl implements FeeDao {

	@Override
	public Fee readById(Integer id) {
		return FeeDataTest.getExpectedFee();
	}

	@Override
	public void saveFee(Fee fee) {
	}

	@Override
	public void updateFee(Fee fee) {
	}

	@Override
	public void deleteFee(Integer id) {
	}
}
