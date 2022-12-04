package team.asd.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import team.asd.entity.Fee;

import java.util.List;

@Mapper
public interface FeeMapper {

	Fee readFeeById(Integer id);

	List<Fee> readFeesByParams(@Param("feeType") String feeType, @Param("productId") Integer productId, @Param("state") String state);

	void insertFee(Fee fee);

	void insertFees(List<Fee> fees);

	void updateFee(Fee fee);

	void deleteFee(Integer id);
}
