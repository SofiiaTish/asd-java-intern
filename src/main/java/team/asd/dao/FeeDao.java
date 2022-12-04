package team.asd.dao;

import team.asd.entity.Fee;

import java.util.List;

public interface FeeDao {

	Fee readById(Integer id);

	List<Fee> readFeesByParams(String feeType, Integer productId, String state);

	void saveFee(Fee fee);

	void saveFees(List<Fee> fees);

	void updateFee(Fee fee);

	void deleteFee(Integer id);

}
