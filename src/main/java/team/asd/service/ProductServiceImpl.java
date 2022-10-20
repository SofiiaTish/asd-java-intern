package team.asd.service;

import lombok.NonNull;
import org.apache.commons.collections4.CollectionUtils;
import team.asd.constants.ProductState;
import team.asd.entities.IsProduct;
import team.asd.exceptions.WrongProductException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class ProductServiceImpl implements IsProductService {

	@Override
	@NonNull
	public List<String> defineProductNames(List<IsProduct> productList) throws WrongProductException {
		if (CollectionUtils.isEmpty(productList)) {
			return Collections.emptyList();
		}
		List<String> productNames = new ArrayList<>();
		for (IsProduct p : productList) {
			if (p == null) {
				throw new WrongProductException("Product is null");
			}
			productNames.add(p.getName());
		}
		return productNames;
	}

	@Override
	public List<IsProduct> defineProductsWithCreatedState(List<IsProduct> productList) {
		if (productList == null) {
			return Collections.emptyList();
		}
		return productList.stream()
				.filter(Objects::nonNull)
				.filter(p -> p.getState() == ProductState.Created)
				.collect(Collectors.toList());
	}

	@Override
	@NonNull
	public Map<ProductState, Integer> calculateProductCountByState(List<IsProduct> productList) throws WrongProductException {
		if (productList == null) {
			Map<ProductState, Integer> map = new HashMap<>();
			map.put(ProductState.Created, 0);
			map.put(ProductState.Final, 0);
			map.put(ProductState.Suspended, 0);
			map.put(ProductState.Deprecated, 0);
			return map;
		}
		if (productList.contains(null)) {
			throw new WrongProductException("Null product in list");
		}
		return productList.stream()
				.collect(Collectors.groupingBy(IsProduct::getState, Collectors.reducing(0, e -> 1, Integer::sum)));
	}

	@Override
	@NonNull
	public List<IsProduct> filterProductsByProvidedObject(List<IsProduct> productList, IsProduct product) throws WrongProductException {
		if (productList == null) {
			return Collections.emptyList();
		}
		if (product == null) {
			throw new WrongProductException("Product is null");
		}
		if (product.getName() == null) {
			return productList.stream()
					.filter(p -> p.getState() == product.getState())
					.collect(Collectors.toList());
		}
		if (product.getState() == null) {
			return productList.stream()
					.filter(p -> p.getName()
							.equals(product.getName()))
					.collect(Collectors.toList());
		}
		return productList.stream()
				.filter(p -> p.getName()
						.equals(product.getName()) && p.getState() == product.getState())
				.collect(Collectors.toList());
	}
}
