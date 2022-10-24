package team.asd.service;

import lombok.NonNull;
import org.apache.commons.collections4.CollectionUtils;
import team.asd.constants.ProductState;
import team.asd.entities.IsProduct;
import team.asd.exceptions.WrongProductException;

import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Collections;
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
		checkListContainsNull(productList);
		return productList.stream()
				.map(IsProduct::getName)
				.collect(Collectors.toList());
	}

	@Override
	public List<IsProduct> defineProductsWithCreatedState(List<IsProduct> productList) {
		if (CollectionUtils.isEmpty(productList)) {
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
		checkListContainsNull(productList);
		return Arrays.stream(ProductState.values())
				.map(e -> new AbstractMap.SimpleEntry<>(e, countProductsWithState(e, productList)))
				.collect(Collectors.toMap(AbstractMap.SimpleEntry::getKey, AbstractMap.SimpleEntry::getValue));
	}

	private void checkListContainsNull(List<IsProduct> list) throws WrongProductException {
		if (CollectionUtils.isNotEmpty(list) && list.contains(null)) {
			throw new WrongProductException("Null product in list");
		}
	}

	private Integer countProductsWithState(ProductState ps, List<IsProduct> list) {
		if (CollectionUtils.isNotEmpty(list))
			return (int) list.stream()
					.filter(p -> p.getState() == ps)
					.count();
		return 0;
	}

	@Override
	@NonNull
	public List<IsProduct> filterProductsByProvidedObject(List<IsProduct> productList, IsProduct product) throws WrongProductException {
		if (CollectionUtils.isEmpty(productList)) {
			return Collections.emptyList();
		}
		if (product == null) {
			throw new WrongProductException("Product is null");
		}
		if (product.getName() == null && product.getState() == null) {
			return productList;
		}
		return productList.stream()
				.filter(p -> (product.getName() == null && p.getState() == product.getState()) || (product.getState() == null && p.getName()
						.equals(product.getName())) || (p.getName()
						.equals(product.getName()) && p.getState() == product.getState()))
				.collect(Collectors.toList());
	}
}
