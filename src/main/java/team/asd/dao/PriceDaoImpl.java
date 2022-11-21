package team.asd.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import team.asd.entity.Price;
import team.asd.mapper.PriceMapper;


@Repository
public class PriceDaoImpl implements PriceDao {

	private final PriceMapper priceMapper;

	public PriceDaoImpl(@Autowired PriceMapper priceMapper) {
		this.priceMapper = priceMapper;
	}

	@Override
	@Transactional
	public Price readById(Integer id) {
		return priceMapper.readPriceById(id);
	}

	@Override
	@Transactional
	public void savePrice(Price price) {
		priceMapper.insertPrice(price);
	}

	@Override
	@Transactional
	public void updatePrice(Price price) {
		priceMapper.updatePrice(price);
	}

	@Override
	@Transactional
	public void deletePrice(Integer id) {
		priceMapper.deletePrice(id);
	}
}
