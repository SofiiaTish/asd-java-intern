package team.asd.constant;

import java.util.Arrays;

public enum Unit {

	Per_Stay(1), Per_Day(2), Per_Person(3),
	Per_Day_Person(4), Per_Person_Extra(5), Per_Day_Person_Extra(6);

	private final Integer value;

	Unit(Integer value) {
		this.value = value;
	}

	public Integer getValue() {
		return this.value;
	}

	public static Unit getByInt(Integer value) {
		return Arrays.stream(Unit.values())
				.filter(tax -> value.equals(tax.getValue())).findFirst().get();
	}
}
