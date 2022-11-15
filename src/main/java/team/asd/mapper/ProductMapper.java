package team.asd.mapper;

import team.asd.entity.Product;

public interface ProductMapper {
	Product readProductById(Integer id);
	Product insertProduct(Product product);
	Product updateProduct(Product product);
	Integer deleteProduct(Product product);
}
