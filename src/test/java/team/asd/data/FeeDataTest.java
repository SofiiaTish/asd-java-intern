package team.asd.data;

import team.asd.constant.FeeState;
import team.asd.constant.FeeType;
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
				.fromDate(Date.from(Instant.now()))
				.toDate(Date.from(Instant.parse("2022-12-15T10:15:30Z")))
				.taxType(2)
				.unit(3)
				.value(34.56)
				.valueType(1)
				.currency("usd")
				.build();

	}
}
