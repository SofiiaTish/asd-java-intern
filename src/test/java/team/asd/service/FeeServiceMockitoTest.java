package team.asd.service;

import org.junit.jupiter.api.AfterEach;
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
import team.asd.entity.Fee;
import team.asd.exception.ValidationException;

import java.time.Instant;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
class FeeServiceMockitoTest {

	private FeeService feeService;

	@Mock
	private FeeDao feeDao;

	private static Fee fee;
	private AutoCloseable mockClosable;

	@BeforeEach
	void setUp() {
		fee = null;
		mockClosable = MockitoAnnotations.openMocks(this);
		feeService = new FeeService(feeDao);
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
		Mockito.when(feeDao.readById(1))
				.thenReturn(Fee.builder().id(1).build());
		Fee fee = feeService.readById(1);
		assertNotNull(fee);
		assertEquals(1, fee.getId());
	}

	@Test
	void testCreateFee() {
		Exception e = assertThrows(ValidationException.class, () -> Whitebox.invokeMethod(feeService, "validFee", fee, false));
		assertEquals("Fee is null", e.getMessage());

		Mockito.doAnswer(invocation -> {
			fee = Fee.builder().id(1).build();
			return null;
		}).when(feeDao).saveFee(Mockito.any(Fee.class));
		Mockito.when(feeDao.readById(1)).thenAnswer(invocation -> fee);

		feeService.createFee(Fee.builder()
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
		assertNotNull(feeService.readById(1));

		Mockito.verify(feeDao).saveFee(Mockito.any());
		Mockito.verify(feeDao, Mockito.times(2)).readById(Mockito.anyInt());
	}

	@Test
	void testUpdateFee() {
		Mockito.doAnswer(invocation -> {
			fee = Fee.builder().id(1).build();
			return null;
		}).when(feeDao).updateFee(Mockito.any(Fee.class));
		Mockito.when(feeDao.readById(1)).thenAnswer(invocation -> fee);

		feeService.updateFee(Fee.builder().id(1).build());
		assertNotNull(feeService.readById(1));

		Mockito.verify(feeDao).updateFee(Mockito.any());
		Mockito.verify(feeDao, Mockito.times(2)).readById(Mockito.anyInt());
	}

	@Test
	void testDeleteFee() {
		feeService.deleteFee(1);
		Mockito.verify(feeDao).deleteFee(Mockito.anyInt());
	}
}