package team.asd.util;

import team.asd.constant.*;
import team.asd.dto.*;
import team.asd.entity.Fee;
import team.asd.entity.Price;
import team.asd.entity.Product;
import team.asd.exception.ValidationException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ConverterUtil {
	private static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

	public static Product convertDtoToProduct(ProductDto productDto) {
		return productDto == null ? null : Product.builder()
				.id(productDto.getId())
				.supplierId(productDto.getSupplierId())
				.name(productDto.getName())
				.state(productDto.getState() == null ? null : ProductState.valueOf(productDto.getState()))
				.currency(productDto.getCurrency())
				.guestsNumber(productDto.getGuestsNumber())
				.longitude(productDto.getLongitude())
				.latitude(productDto.getLatitude())
				.physicalAddress(productDto.getPhysicalAddress())
				.build();
	}

	public static ProductDto convertProductToDto(Product product) {
		return ProductDto.builder()
				.id(product.getId())
				.supplierId(product.getSupplierId())
				.name(product.getName())
				.state(product.getState() != null ? product.getState().toString() : null)
				.currency(product.getCurrency())
				.guestsNumber(product.getGuestsNumber())
				.longitude(product.getLongitude())
				.latitude(product.getLatitude())
				.physicalAddress(product.getPhysicalAddress())
				.build();
	}

	public static Price convertDtoToPrice(PriceDto priceDto) {
		Price price = priceDto == null ? null : Price.builder()
				.id(priceDto.getId())
				.entityType(priceDto.getEntityType())
				.entityId(priceDto.getEntityId())
				.name(priceDto.getName())
				.state(priceDto.getState() == null ? null : PriceState.valueOf(priceDto.getState()))
				.value(priceDto.getValue())
				.currency(priceDto.getCurrency())
				.build();
		if (price != null) {
			try {
				price.setFromDate(priceDto.getFromDate() == null ? null : format.parse(priceDto.getFromDate()));
			} catch (ParseException e) {
				throw new ValidationException("Invalid date of price");
			}
			try {
				price.setToDate(priceDto.getToDate() == null ? null : format.parse(priceDto.getToDate()));
			} catch (ParseException e1) {
				throw new ValidationException("Invalid date of price");
			}
		}
		return price;
	}

	public static PriceDto convertPriceToDto(Price price) {
		return PriceDto.builder()
				.id(price.getId())
				.entityType(price.getEntityType())
				.entityId(price.getEntityId())
				.name(price.getName())
				.state(price.getState() != null ? price.getState().toString() : null)
				.value(price.getValue())
				.fromDate(price.getFromDate() != null ? price.getFromDate().toString() : null)
				.toDate(price.getToDate() != null ? price.getToDate().toString() : null)
				.currency(price.getCurrency())
				.build();
	}

	public static Fee convertDtoToFee(FeeDto feeDto) {
		Fee fee = feeDto == null ? null : Fee.builder()
				.id(feeDto.getId())
				.feeType(feeDto.getFeeType() == null ? null : FeeType.valueOf(feeDto.getFeeType()))
				.productId(feeDto.getProductId())
				.name(feeDto.getName())
				.state(feeDto.getState() == null ? null : FeeState.valueOf(feeDto.getState()))
				.taxType(feeDto.getTaxType() == null ? null : TaxType.valueOf(feeDto.getTaxType()))
				.unit(feeDto.getUnit() == null ? null : Unit.valueOf(feeDto.getUnit()))
				.value(feeDto.getValue())
				.valueType(feeDto.getValueType() == null ? null : ValueType.valueOf(feeDto.getValueType()))
				.currency(feeDto.getCurrency())
				.build();
		if (fee != null) {
			try {
				fee.setFromDate(feeDto.getFromDate() == null ? null : format.parse(feeDto.getFromDate()));
			} catch (ParseException e) {
				throw new ValidationException("Invalid from_date of fee");
			}
			try {
				fee.setToDate(feeDto.getToDate() == null ? null : format.parse(feeDto.getToDate()));
			} catch (ParseException e) {
				throw new ValidationException("Invalid to_date of fee");
			}
		}
		return fee;
	}

	public static FeeDto convertFeeToDto(Fee fee) {
		return FeeDto.builder()
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

	}

	public static Date convertStringToDate(String date) {
		try {
			return date == null ? null : format.parse(date);
		} catch (ParseException e) {
			throw new ValidationException("Invalid date of price");
		}
	}
}
