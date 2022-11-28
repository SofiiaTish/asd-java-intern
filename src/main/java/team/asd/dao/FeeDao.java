package team.asd.dao;

import team.asd.entity.Fee;

public interface FeeDao {

	Fee readById(Integer id);

	void saveFee(Fee fee);

	void updateFee(Fee fee);

	void deleteFee(Integer id);

}
