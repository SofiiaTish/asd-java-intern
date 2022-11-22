package team.asd.constant;

import java.util.Arrays;

public enum ValueType {
	Flat(1), Percent(2);

	private final Integer value;

	ValueType(Integer value) {
		this.value = value;
	}

	public Integer getValue() {
		return this.value;
	}

	public static ValueType getByInt(Integer value) {
		return Arrays.stream(ValueType.values())
				.filter(tax -> value.equals(tax.getValue())).findFirst().get();
	}
}
