package team.asd.mapper;

import org.apache.ibatis.annotations.Mapper;
import team.asd.entity.Price;

@Mapper
public interface PriceMapper {
	Price readPriceById(Integer id);

	void insertPrice(Price price);

	void updatePrice(Price price);

	void deletePrice(Integer id);
}
