package team.asd.constant;

import java.util.Arrays;

public enum TaxType {
	Taxable(1), Not_Taxable(2);

	private final Integer value;

	TaxType(Integer value) {
		this.value = value;
	}

	public Integer getValue() {
		return this.value;
	}

	public static TaxType getByInt(Integer value) {
		return Arrays.stream(TaxType.values())
				.filter(tax -> value.equals(tax.getValue())).findFirst().get();
	}
}
