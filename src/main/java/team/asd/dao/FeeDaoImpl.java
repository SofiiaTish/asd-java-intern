package team.asd.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import team.asd.entity.Fee;
import team.asd.mapper.FeeMapper;


@Repository
public class FeeDaoImpl implements FeeDao {

	private final FeeMapper feeMapper;

	public FeeDaoImpl(@Autowired FeeMapper feeMapper) {
		this.feeMapper = feeMapper;
	}

	@Override
	public Fee readById(Integer id) {
		return feeMapper.readFeeById(id);
	}

	@Override
	public void saveFee(Fee fee) {
		feeMapper.insertFee(fee);
	}

	@Override
	public void updateFee(Fee fee) {
		feeMapper.updateFee(fee);
	}

	@Override
	public void deleteFee(Integer id) {
		feeMapper.deleteFee(id);
	}
}
