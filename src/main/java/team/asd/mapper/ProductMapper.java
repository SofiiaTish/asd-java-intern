package team.asd.mapper;

import org.apache.ibatis.annotations.Mapper;
import team.asd.entity.Product;

@Mapper
public interface ProductMapper {
	Product readProductById(Integer id);

	void insertProduct(Product product);

	void updateProduct(Product product);

	void deleteProduct(Integer id);
}
