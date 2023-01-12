package team.asd.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import team.asd.entity.Fee;

import java.util.Date;
import java.util.List;

@Mapper
public interface FeeMapper {

	Fee readFeeById(Integer id);

	List<Fee> readFeesByParams(@Param("feeType") Integer feeType, @Param("productId") Integer productId, @Param("state") String state);

	List<Fee> readFeesByDateRange(@Param("productId") Integer productId, @Param("fromDate") Date fromDate, @Param("toDate") Date toDate);

	List<Fee> readFeesByValueProductSupplierId(@Param("minValue") Integer minValue, @Param("supplierId") Integer supplierId);

	void insertFee(Fee fee);

	void insertFees(List<Fee> fees);

	void updateFee(Fee fee);

	void deleteFee(Integer id);
}
