package team.asd.dao;

import team.asd.entity.Fee;

import java.util.Date;
import java.util.List;

public interface FeeDao {

	Fee readById(Integer id);

	List<Fee> readFeesByParams(Integer feeType, Integer productId, String state);

	List<Fee> readFeesByDateRange(Date fromDate, Date toDate);

	void saveFee(Fee fee);

	void saveFees(List<Fee> fees);

	void updateFee(Fee fee);

	void deleteFee(Integer id);

}
