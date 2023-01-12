package team.asd.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import team.asd.dto.FeeDto;
import team.asd.exception.ValidationException;
import team.asd.service.FeeService;
import team.asd.util.ConverterUtil;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = {"/fee"})
@Validated
@ApiOperation("Fee Api")
public class FeeController {
	private final FeeService feeService;

	public FeeController(@Autowired FeeService feeService) {
		this.feeService = feeService;
	}

	@ApiOperation(value = "Get fee by id", notes = "Require integer path variable. Return fee by id as JSON object")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Fee was found"),
			@ApiResponse(code = 404, message = "Custom message about error reasons or unknown error")
	})
	@GetMapping(path = {"/{id}"})
	public FeeDto getFeeById(@PathVariable @ApiParam(value = "Fee Id", example = "1") Integer id) {
		return ConverterUtil.convertFeeToDto(feeService.readById(id));
	}

	@ApiOperation(value = "Get fees by fee type, product id and state in different combination",
			notes = "Can require fee type, product id and state as request parameters. Return list of fees that match to parameters as JSON objects.")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Fees exist in table"),
			@ApiResponse(code = 404, message = "Custom message about error reasons or unknown error")
	})
	@GetMapping(path = {"/fees"})
	public List<FeeDto> getFeeByFeeTypeProductIdState(
			@RequestParam(required = false) @ApiParam(value = "Fee type", example = "General") Integer feeType,
			@RequestParam @ApiParam(value = "Product id", example = "4") Integer productId,
			@RequestParam(required = false) @ApiParam(value = "Fee state", example = "Created") String state) {
		return feeService.readByParams(feeType, productId, state).stream().map(ConverterUtil::convertFeeToDto).collect(Collectors.toList());
	}

	@ApiOperation(value = "Get fees by date range for current product.",
			notes = "Require two different dates and productId as request parameters. Return list of fees witch date ranges are inside specified range as JSON objects. Parameters have to be in order 'start date, end date'.")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Fees exist in table"),
			@ApiResponse(code = 404, message = "Custom message about error reasons or unknown error")
	})
	@GetMapping(path = {"/fees/date-range"})
	public List<FeeDto> getFeesByDateRange(
			@RequestParam @ApiParam(value = "Product id", example = "4") Integer productId,
			@RequestParam @ApiParam(value = "Start date of range", example = "2022-10-10")
			@Pattern(regexp = "[0-9]{4}-[0-2]{2}-[0-9]{2}", message = "{date.format}") String fromDate,
			@RequestParam @ApiParam(value = "End date of range", example = "2022-10-20")
			@Pattern(regexp = "[0-9]{4}-[0-2]{2}-[0-9]{2}", message = "{date.format}") String toDate) {
		return feeService.readByDateRange(productId, ConverterUtil.convertStringToDate(fromDate), ConverterUtil.convertStringToDate(toDate))
				.stream().map(ConverterUtil::convertFeeToDto).collect(Collectors.toList());
	}

	@GetMapping(path = {"/fees/min-value"})
	public List<FeeDto> getFeesByMinValueSupplierId(@RequestParam Integer minValue, @RequestParam Integer supplierId) {
		return feeService.readByMinValueSupplierId(minValue, supplierId).stream().map(ConverterUtil::convertFeeToDto).collect(Collectors.toList());
	}

	@ApiOperation(value = "Add new fee to table", notes = "Require JSON object with Fee fields. Return created fee as JSON object")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successful creation"),
			@ApiResponse(code = 404, message = "Custom message about error reasons or unknown error")
	})
	@PostMapping(path = {"/"})
	public FeeDto createPrice(@RequestBody @ApiParam(name = "feeForCreation", value = "Fee data") @Valid FeeDto feeDto) {
		return ConverterUtil.convertFeeToDto(feeService.createFee(ConverterUtil.convertDtoToFee(feeDto)));
	}

	@ApiOperation(value = "Add several new fees to table", notes = "Require list of JSON objects with Fee fields. Can`t process empty list.")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successful creation"),
			@ApiResponse(code = 404, message = "Custom message about error reasons or unknown error")
	})
	@PostMapping(path = {"/fees"})
	public void storePrice(@RequestBody @ApiParam(name = "fees", value = "List of fees to store") List<@Valid FeeDto> feeDtoList) {
		if (CollectionUtils.isEmpty(feeDtoList)) {
			throw new ValidationException("List of fees is empty");
		} else {
			feeService.createFees(feeDtoList.stream().map(ConverterUtil::convertDtoToFee).collect(Collectors.toList()));
		}
	}

	@ApiOperation(value = "Update fee by id", notes = "Require fee data to update. Update not null fields. Return updated fee as JSON object")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successful update"),
			@ApiResponse(code = 404, message = "Custom message about error reasons or unknown error")
	})
	@PutMapping(path = {"/"})
	public FeeDto updatePrice(@RequestBody @ApiParam(name = "feeForUpdate", value = "Fee data") @Valid FeeDto feeDto) {
		return ConverterUtil.convertFeeToDto(feeService.updateFee(ConverterUtil.convertDtoToFee(feeDto)));
	}

	@ApiOperation(value = "Delete fee by id", notes = "Require integer path variable. Update column state in table on 'Final'")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successful deleting"),
			@ApiResponse(code = 404, message = "Custom message about error reasons or unknown error")
	})
	@DeleteMapping(path = {"/{id}"})
	public void deleteFeeById(@PathVariable @ApiParam(value = "Fee Id", example = "2") Integer id) {
		feeService.deleteFee(id);
	}
}
