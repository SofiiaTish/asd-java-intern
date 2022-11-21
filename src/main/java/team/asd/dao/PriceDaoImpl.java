package team.asd.dao;

import org.springframework.stereotype.Repository;
import team.asd.entity.Price;


@Repository
public class PriceDaoImpl implements PriceDao {

	@Override
	public Price readById(Integer id) {
		return null;
	}

	@Override
	public void savePrice(Price price) {
	}

	@Override
	public void updatePrice(Price price) {
	}

	@Override
	public void deletePrice(Integer id) {
	}
}
