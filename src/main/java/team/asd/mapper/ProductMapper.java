package team.asd.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import team.asd.dto.ProductReport;
import team.asd.entity.Product;

import java.util.List;

@Mapper
public interface ProductMapper {
	Product readProductById(Integer id);

	List<Product> readProductsByParams(@Param("supplierId") Integer supplierId, @Param("name") String name, @Param("state") String state);

	ProductReport readProductReportById(Integer id);

	void insertProduct(Product product);

	void insertProducts(List<Product> products);

	void updateProduct(Product product);

	void deleteProduct(Integer id);
}
