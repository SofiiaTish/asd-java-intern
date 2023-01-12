package team.asd.dao;

import team.asd.entity.Fee;

import java.util.Date;
import java.util.List;

public interface FeeDao {

	Fee readById(Integer id);

	List<Fee> readFeesByParams(Integer feeType, Integer productId, String state);

	List<Fee> readFeesByDateRange(Integer productId, Date fromDate, Date toDate);

	List<Fee> readFeesByValueProductSupplierId(Integer minValue, Integer supplierId);

	void saveFee(Fee fee);

	void saveFees(List<Fee> fees);

	void updateFee(Fee fee);

	void deleteFee(Integer id);

}
