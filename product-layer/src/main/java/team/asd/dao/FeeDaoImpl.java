package team.asd.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import team.asd.entity.Fee;
import team.asd.mapper.FeeMapper;

import java.util.Date;
import java.util.List;


@Repository
public class FeeDaoImpl implements FeeDao {

	private final FeeMapper feeMapper;

	public FeeDaoImpl(@Autowired FeeMapper feeMapper) {
		this.feeMapper = feeMapper;
	}

	@Override
	@Transactional
	public Fee readById(Integer id) {
		return feeMapper.readFeeById(id);
	}

	@Override
	@Transactional
	public List<Fee> readFeesByParams(Integer feeType, Integer productId, String state) {
		return feeMapper.readFeesByParams(feeType, productId, state);
	}

	@Override
	@Transactional
	public List<Fee> readFeesByDateRange(Integer productId, Date fromDate, Date toDate) {
		return feeMapper.readFeesByDateRange(productId,fromDate, toDate);
	}

	@Override
	public List<Fee> readFeesByValueProductSupplierId(Integer minValue, Integer supplierId) {
		return feeMapper.readFeesByValueProductSupplierId(minValue, supplierId);
	}

	@Override
	@Transactional
	public void saveFee(Fee fee) {
		feeMapper.insertFee(fee);
	}

	@Override
	@Transactional
	public void saveFees(List<Fee> fees) {
		feeMapper.insertFees(fees);
	}

	@Override
	@Transactional
	public void updateFee(Fee fee) {
		feeMapper.updateFee(fee);
	}

	@Override
	@Transactional
	public void deleteFee(Integer id) {
		feeMapper.deleteFee(id);
	}
}
