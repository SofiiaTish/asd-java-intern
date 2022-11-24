package team.asd.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import team.asd.dao.FeeDaoTestImpl;
import team.asd.data.FeeDataTest;
import team.asd.entity.Fee;
import team.asd.exception.ValidationException;

import java.time.Instant;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class FeeServiceTest {

	private static FeeService feeService;

	private static Fee fee;

	@BeforeAll
	public static void setUpClass() {
		feeService = new FeeService(new FeeDaoTestImpl());
	}

	@BeforeEach
	void setUp() {
		fee = FeeDataTest.getExpectedFee();
	}

	@Test
	void testReadById() {
		assertEquals(fee.getId(), feeService.readById(1).getId());
		assertThrows(ValidationException.class, () -> feeService.readById(-1));
		assertThrows(ValidationException.class, () -> feeService.readById(null));
	}

	@Test
	void testCreateFee() {
		assertEquals(fee.getId(), feeService.createFee(fee).getId());
		assertThrows(ValidationException.class, () -> feeService.createFee(null));
		assertThrows(ValidationException.class, () -> feeService.createFee(new Fee()));
		fee.setCurrency(null);
		assertThrows(ValidationException.class, () -> feeService.createFee(fee));
		fee.setCurrency("usd");
		fee.setToDate(Date.from(Instant.now()));
		fee.setFromDate(Date.from(Instant.parse("2022-12-15T10:15:30Z")));
		assertThrows(ValidationException.class, () -> feeService.createFee(fee));
	}

	@Test
	void testUpdateFee() {
		assertEquals(fee.getId(), feeService.updateFee(fee).getId());
		assertThrows(ValidationException.class, () -> feeService.updateFee(null));
		assertThrows(ValidationException.class, () -> feeService.updateFee(new Fee()));
		fee.setId(null);
		assertThrows(ValidationException.class, () -> feeService.updateFee(fee));
	}

	@Test
	void testUpdateFeeWithNullFromDate(){
		fee.setToDate(Date.from(Instant.parse("2022-10-15T10:15:30Z")));
		fee.setFromDate(null);
		assertThrows(ValidationException.class, () -> feeService.updateFee(fee));
	}

	@Test
	void testUpdateFeeWithNullToDate(){
		fee.setToDate(null);
		fee.setFromDate(Date.from(Instant.parse("2022-10-15T10:15:30Z")));
		assertThrows(ValidationException.class, () -> feeService.updateFee(fee));
	}

	@Test
	void testUpdateFeeWithReverseDates(){
		fee.setToDate(Date.from(Instant.now()));
		fee.setFromDate(Date.from(Instant.now()));
		assertThrows(ValidationException.class, () -> feeService.updateFee(fee));
	}

	@Test
	void testDeleteFee() {
		assertThrows(ValidationException.class, () -> feeService.deleteFee(-1));
		assertThrows(ValidationException.class, () -> feeService.deleteFee(null));
	}
}