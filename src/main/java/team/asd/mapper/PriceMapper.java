package team.asd.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import team.asd.entity.Price;

import java.util.List;

@Mapper
public interface PriceMapper {
	Price readPriceById(Integer id);

	List<Price> readPricesByParams(@Param("entityType") String entityType, @Param("entityId") Integer entityId, @Param("state") String state);

	void insertPrice(Price price);

	void insertPrices(List<Price> prices);

	void updatePrice(Price price);

	void deletePrice(Integer id);
}
