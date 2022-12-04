package team.asd.controller;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import team.asd.dto.FeeDto;
import team.asd.exception.ValidationException;
import team.asd.service.FeeService;
import team.asd.util.ConverterUtil;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = {"/fee"})
@Validated
public class FeeController {
	private final FeeService feeService;

	public FeeController(@Autowired FeeService feeService) {
		this.feeService = feeService;
	}

	@GetMapping(path = {"/{id}"})
	public FeeDto getFeeById(@PathVariable Integer id) {
		return ConverterUtil.convertFeeToDto(feeService.readById(id));
	}

	@GetMapping(path = {"/fees"})
	public List<FeeDto> getFeeByFeeTypeProductIdState(
			@RequestParam(required = false) String feeType,
			Integer productId,
			@RequestParam(required = false) String state) {
		return feeService.readByParams(feeType, productId, state).stream().map(ConverterUtil::convertFeeToDto).collect(Collectors.toList());
	}

	@PostMapping(path = {"/"})
	public FeeDto createPrice(@RequestBody @Valid FeeDto feeDto) {
		return ConverterUtil.convertFeeToDto(feeService.createFee(ConverterUtil.convertDtoToFee(feeDto)));
	}

	@PostMapping(path = {"/fees"})
	public void storePrice(@RequestBody List<@Valid FeeDto> feeDtoList) {
		if (CollectionUtils.isEmpty(feeDtoList)) {
			throw new ValidationException("List of fees is empty");
		} else {
			feeService.createFees(feeDtoList.stream().map(ConverterUtil::convertDtoToFee).collect(Collectors.toList()));
		}
	}

	@PutMapping(path = {"/"})
	public FeeDto updatePrice(@RequestBody @Valid FeeDto feeDto) {
		return ConverterUtil.convertFeeToDto(feeService.updateFee(ConverterUtil.convertDtoToFee(feeDto)));
	}

	@DeleteMapping(path = {"/{id}"})
	public void deleteFeeById(@PathVariable Integer id) {
		feeService.deleteFee(id);
	}
}
