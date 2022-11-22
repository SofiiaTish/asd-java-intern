package team.asd.util;

import team.asd.constant.*;
import team.asd.dto.FeeDto;
import team.asd.dto.PriceDto;
import team.asd.dto.ProductDto;
import team.asd.entity.Fee;
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
			price.setToDate(format.parse(priceDto.getToDate()));
		} catch (NullPointerException e3) {
			price.setToDate(null);
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
				.fromDate(price.getFromDate() != null ? price.getFromDate().toString() : null)
				.toDate(price.getToDate() != null ? price.getToDate().toString() : null)
				.currency(price.getCurrency())
				.build();
		try {
			priceDto.setState(price.getState().toString());
		} catch (NullPointerException e) {
			priceDto.setState(null);
		}
		return priceDto;
	}

	public static Fee convertDtoToFee(FeeDto feeDto) {
		Fee fee = Fee.builder()
				.id(feeDto.getId())
				.feeType(feeDto.getFeeType() == null ? null : FeeType.valueOf(feeDto.getFeeType()))
				.productId(feeDto.getProductId())
				.name(feeDto.getName())
				.taxType(feeDto.getTaxType() == null ? null : TaxType.valueOf(feeDto.getTaxType()))
				.unit(feeDto.getUnit() == null ? null : Unit.valueOf(feeDto.getUnit()))
				.value(feeDto.getValue())
				.valueType(feeDto.getValueType() == null ? null : ValueType.valueOf(feeDto.getValueType()))
				.currency(feeDto.getCurrency())
				.build();
		try {
			fee.setState(FeeState.valueOf(feeDto.getState()));
		} catch (NullPointerException e) {
			fee.setState(null);
		} catch (IllegalArgumentException e) {
			throw new ValidationException("Invalid state of fee");
		}
		try {
			fee.setFromDate(format.parse(feeDto.getFromDate()));
		} catch (NullPointerException e) {
			fee.setFromDate(null);
		} catch (ParseException e) {
			throw new ValidationException("Invalid from_date of fee");
		}
		try {
			fee.setToDate(format.parse(feeDto.getToDate()));
		} catch (NullPointerException e) {
			fee.setToDate(null);
		} catch (ParseException e) {
			throw new ValidationException("Invalid to_date of fee");
		}
		return fee;
	}

	public static FeeDto convertFeeToDto(Fee fee) {
		FeeDto feeDto = FeeDto.builder()
				.id(fee.getId())
				.feeType(fee.getFeeType() != null ? fee.getFeeType().toString() : null)
				.productId(fee.getProductId())
				.name(fee.getName())
				.state(fee.getState() != null ? fee.getState().toString() : null)
				.fromDate(fee.getFromDate() != null ? fee.getFromDate().toString() : null)
				.toDate(fee.getToDate() != null ? fee.getToDate().toString() : null)
				.taxType(fee.getTaxType().toString())
				.unit(fee.getUnit() != null ? fee.getUnit().toString() : null)
				.value(fee.getValue())
				.valueType(fee.getValueType() != null ? fee.getValueType().toString() : null)
				.currency(fee.getCurrency())
				.build();
		return feeDto;
	}
}
