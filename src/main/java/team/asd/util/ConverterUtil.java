package team.asd.util;

import team.asd.constant.ProductState;
import team.asd.dto.ProductDto;
import team.asd.entity.Product;
import team.asd.exception.ValidationException;

public class ConverterUtil {

	public static Product convertDtoToProduct(ProductDto productDto) {
		Product product = Product.builder().id(productDto.getId()).supplierId(productDto.getSupplierId()).name(productDto.getName())
				.currency(productDto.getCurrency()).guestsNumber(productDto.getGuestsNumber()).longitude(productDto.getLongitude())
				.latitude(productDto.getLatitude()).physicalAddress(productDto.getPhysicalAddress()).build();
		try {
			product.setState(ProductState.valueOf(productDto.getState()));
		} catch (NullPointerException e3) {
			product.setState(null);
		} catch (IllegalArgumentException e1) {
			throw new ValidationException("Invalid state of product");
		}
		return product;
	}

	public static ProductDto convertProductToDto(Product product) {
		ProductDto productDto = ProductDto.builder().id(product.getId()).supplierId(product.getSupplierId()).name(product.getName())
				.currency(product.getCurrency()).guestsNumber(product.getGuestsNumber()).longitude(product.getLongitude()).latitude(product.getLatitude())
				.physicalAddress(product.getPhysicalAddress()).build();
		try {
			productDto.setState(product.getState().toString());
		} catch (NullPointerException e) {
			productDto.setState(null);
		}
		return productDto;
	}
}
