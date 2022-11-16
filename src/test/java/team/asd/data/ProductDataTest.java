package team.asd.data;

import team.asd.entity.Product;

public class ProductDataTest {

	private static Product expectedProduct;

	public static Product getExpectedProduct() {
		if (expectedProduct == null) {
			expectedProduct = new Product();
			expectedProduct.setId(2);
			expectedProduct.setSupplierId(4);
			expectedProduct.setName("Exp");
			expectedProduct.setCurrency("usd");

		}
		return expectedProduct;
	}
}
