package team.asd.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.powermock.reflect.Whitebox;
import team.asd.constant.*;
import team.asd.dao.FeeDao;
import team.asd.dao.FeeDaoTestImpl;
import team.asd.data.FeeDataTest;
import team.asd.entity.Fee;
import team.asd.exception.ValidationException;

import java.time.Instant;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@RunWith(MockitoJUnitRunner.class)
class FeeServiceTest {
	private static FeeService feeService;
	private FeeService mockFeeService;

	@Mock
	private FeeDao mockFeeDao;

	private static Fee fee;
	private static Fee mockFee;
	private AutoCloseable mockClosable;

	@BeforeAll
	public static void setUpClass() {
		feeService = new FeeService(new FeeDaoTestImpl());
	}

	@BeforeEach
	void setUp() {
		fee = FeeDataTest.getExpectedFee();
		mockFee = null;
		mockClosable = MockitoAnnotations.openMocks(this);
		mockFeeService = new FeeService(mockFeeDao);
	}

	@AfterEach
	void tearDown() {
		try {
			mockClosable.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Test
	void testReadById() {
		assertEquals(fee.getId(), feeService.readById(1).getId());
		assertThrows(ValidationException.class, () -> feeService.readById(-1));
		assertThrows(ValidationException.class, () -> feeService.readById(null));

		Mockito.when(mockFeeDao.readById(1))
				.thenReturn(Fee.builder().id(1).build());
		Fee fee = mockFeeService.readById(1);
		assertNotNull(fee);
		assertEquals(1, fee.getId());
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

		Exception e = assertThrows(ValidationException.class, () -> Whitebox.invokeMethod(mockFeeService, "validFee", mockFee, false));
		assertEquals("Fee is null", e.getMessage());

		Mockito.doAnswer(invocation -> {
			mockFee = Fee.builder().id(1).build();
			return null;
		}).when(mockFeeDao).saveFee(Mockito.any(Fee.class));
		Mockito.when(mockFeeDao.readById(1)).thenAnswer(invocation -> mockFee);

		assertNull(mockFeeService.readById(1));
		mockFeeService.createFee(Fee.builder()
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
				.build());
		assertNotNull(mockFeeService.readById(1));

		Mockito.verify(mockFeeDao).saveFee(Mockito.any());
		Mockito.verify(mockFeeDao, Mockito.atLeast(2)).readById(Mockito.anyInt());
	}

	@Test
	void testUpdateFee() {
		assertEquals(fee.getId(), feeService.updateFee(fee).getId());
		assertThrows(ValidationException.class, () -> feeService.updateFee(null));
		assertThrows(ValidationException.class, () -> feeService.updateFee(new Fee()));
		fee.setId(null);
		assertThrows(ValidationException.class, () -> feeService.updateFee(fee));

		Mockito.doAnswer(invocation -> {
			mockFee = Fee.builder().id(1).build();
			return null;
		}).when(mockFeeDao).updateFee(Mockito.any(Fee.class));
		Mockito.when(mockFeeDao.readById(1)).thenAnswer(invocation -> mockFee);

		assertNull(mockFeeService.readById(1));
		mockFeeService.updateFee(Fee.builder().id(1).build());
		assertNotNull(mockFeeService.readById(1));

		Mockito.verify(mockFeeDao).updateFee(Mockito.any());
		Mockito.verify(mockFeeDao, Mockito.atLeast(2)).readById(Mockito.anyInt());
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

		mockFeeService.deleteFee(1);
		Mockito.verify(mockFeeDao).deleteFee(Mockito.anyInt());
	}
}