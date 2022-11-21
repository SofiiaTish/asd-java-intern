package team.asd.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import team.asd.entity.Price;
import team.asd.mapper.PriceMapper;


@Repository
public class PriceDaoImpl implements PriceDao {

	private final PriceMapper priceMapper;

	public PriceDaoImpl(@Autowired PriceMapper priceMapper) {
		this.priceMapper = priceMapper;
	}

	@Override
	public Price readById(Integer id) {
		return priceMapper.readPriceById(id);
	}

	@Override
	public void savePrice(Price price) {
		priceMapper.insertPrice(price);
	}

	@Override
	public void updatePrice(Price price) {
		priceMapper.updatePrice(price);
	}

	@Override
	public void deletePrice(Integer id) {
		priceMapper.deletePrice(id);
	}
}
