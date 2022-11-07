package team.asd.util;

import team.asd.constant.ProductState;
import team.asd.dto.ProductDto;
import team.asd.entity.Product;

import java.time.LocalDateTime;

public class ConverterUtil {

    public static Product convertProductDtoToProduct(ProductDto productDto) {
        Product product = new Product();
        product.setProductId(productDto.getProductId());
        product.setSupplierId(productDto.getSupplierId());
        product.setName(productDto.getName());
        product.setState(ProductState.valueOf(productDto.getState()));
        product.setCurrency(productDto.getCurrency());
        product.setGuestsNumber(productDto.getGuestsNumber());
        product.setLongitude(productDto.getLongitude());
        product.setLatitude(productDto.getLatitude());
        product.setPhysicalAddress(productDto.getPhysicalAddress());
        product.setVersion(LocalDateTime.parse(productDto.getVersion()));
        return product;
    }

    public static ProductDto convertProductToProductDto(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setProductId(product.getProductId());
        productDto.setSupplierId(product.getSupplierId());
        productDto.setName(product.getName());
        productDto.setState(product.getState().toString());
        productDto.setCurrency(product.getCurrency());
        productDto.setGuestsNumber(product.getGuestsNumber());
        productDto.setLongitude(product.getLongitude());
        productDto.setLatitude(product.getLatitude());
        productDto.setPhysicalAddress(product.getPhysicalAddress());
        productDto.setVersion(product.getVersion().toString());
        return productDto;
    }
}
