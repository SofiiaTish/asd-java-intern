package team.asd.data;

import team.asd.constant.*;
import team.asd.entity.Fee;

import java.time.Instant;
import java.util.Date;

public class FeeDataTest {
	//private static Fee expectedFee;

	public static Fee getExpectedFee() {

		return Fee.builder()
				.id(1)
				.feeType(FeeType.General)
				.productId(2)
				.name("Test")
				.state(FeeState.Initial)
				.fromDate(Date.from(Instant.parse("2022-12-10T10:15:30Z")))
				.toDate(Date.from(Instant.parse("2022-12-15T10:15:30Z")))
				.taxType(TaxType.Taxable)
				.unit(Unit.Per_Day)
				.value(34.56)
				.valueType(ValueType.Flat)
				.currency("usd")
				.build();

	}
}
