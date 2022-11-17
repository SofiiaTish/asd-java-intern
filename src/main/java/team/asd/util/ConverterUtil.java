package team.asd.util;

import team.asd.constant.PriceState;
import team.asd.constant.ProductState;
import team.asd.dto.PriceDto;
import team.asd.dto.ProductDto;
import team.asd.entity.Price;
import team.asd.entity.Product;
import team.asd.exception.ValidationException;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ConverterUtil {
	private static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

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

	public static Price convertDtoToPrice(PriceDto priceDto) {
		Price price = Price.builder()
				.id(priceDto.getId())
				.entityType(priceDto.getEntityType())
				.entityId(priceDto.getEntityId())
				.name(priceDto.getName())
				.value(priceDto.getValue())
				.currency(priceDto.getCurrency())
				.build();
		try {
			price.setState(PriceState.valueOf(priceDto.getState()));
		} catch (NullPointerException e3) {
			price.setState(null);
		} catch (IllegalArgumentException e1) {
			throw new ValidationException("Invalid state of price");
		}
		try {
			price.setFromDate(format.parse(priceDto.getFromDate()));
		} catch (NullPointerException e3) {
			price.setFromDate(null);
		} catch (ParseException e1) {
			throw new ValidationException("Invalid date of price");
		}
		try {
			price.setFromDate(format.parse(priceDto.getFromDate()));
		} catch (NullPointerException e3) {
			price.setFromDate(null);
		} catch (ParseException e1) {
			throw new ValidationException("Invalid date of price");
		}
		return price;
	}

	public static PriceDto convertPriceToDto(Price price) {
		PriceDto priceDto = PriceDto.builder()
				.id(price.getId())
				.entityType(price.getEntityType())
				.entityId(price.getEntityId())
				.name(price.getName())
				.value(price.getValue())
				.fromDate(price.getFromDate().toString())
				.toDate(price.getToDate().toString())
				.currency(price.getCurrency())
				.build();
		try {
			priceDto.setState(price.getState().toString());
		} catch (NullPointerException e) {
			priceDto.setState(null);
		}
		return priceDto;
	}
}
