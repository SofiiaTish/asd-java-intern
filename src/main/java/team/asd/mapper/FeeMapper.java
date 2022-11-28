package team.asd.mapper;

import org.apache.ibatis.annotations.Mapper;
import team.asd.entity.Fee;

@Mapper
public interface FeeMapper {

	Fee readFeeById(Integer id);

	void insertFee(Fee fee);

	void updateFee(Fee fee);

	void deleteFee(Integer id);
}
