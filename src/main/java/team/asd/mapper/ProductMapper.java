package team.asd.mapper;

import org.apache.ibatis.annotations.Mapper;
import team.asd.entity.Product;

@Mapper
public interface ProductMapper {
	Product readProductById(Integer id);

	Product insertProduct(Product product);

	Product updateProduct(Product product);

	Integer deleteProduct(Integer id);
}
