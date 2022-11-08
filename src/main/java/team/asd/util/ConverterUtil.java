package team.asd.util;

import team.asd.constant.ProductState;
import team.asd.dto.ProductDto;
import team.asd.entity.Product;
import team.asd.exception.ValidationException;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class ConverterUtil {

    public static Product convertDtoToProduct(ProductDto productDto) {
        Product product = Product.builder()
                .id(productDto.getId())
                .supplierId(productDto.getSupplierId())
                .name(productDto.getName())
                .currency(productDto.getCurrency())
                .guestsNumber(productDto.getGuestsNumber())
                .longitude(productDto.getLongitude())
                .latitude(productDto.getLatitude())
                .physicalAddress(productDto.getPhysicalAddress())
                .build();
        try {
            product.setState(ProductState.valueOf(productDto.getState()));
        } catch (NullPointerException e3) {
            e3.printStackTrace();
        } catch (IllegalArgumentException e1) {
            throw new ValidationException("Invalid state of product");
        }
        try {
            product.setVersion(LocalDateTime.parse(productDto.getVersion()));
        } catch (NullPointerException e3) {
            e3.printStackTrace();
        } catch (DateTimeParseException e2) {
            throw new ValidationException("Invalid format of version");
        }
        return product;
    }

    public static ProductDto convertProductToDto(Product product) {
        ProductDto productDto = ProductDto.builder()
                .id(product.getId())
                .supplierId(product.getSupplierId())
                .name(product.getName())
                .currency(product.getCurrency())
                .guestsNumber(product.getGuestsNumber())
                .longitude(product.getLongitude())
                .latitude(product.getLatitude())
                .physicalAddress(product.getPhysicalAddress())
                .build();
        try {
            productDto.setState(product.getState().toString());
        } catch (NullPointerException e) {
            productDto.setState(null);
        }
        try {
            productDto.setVersion(String.valueOf(product.getVersion()));
        } catch (NullPointerException e) {
            productDto.setVersion(null);
        }
        return productDto;
    }
}
