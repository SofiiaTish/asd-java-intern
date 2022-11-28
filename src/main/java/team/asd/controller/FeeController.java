package team.asd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import team.asd.dto.FeeDto;
import team.asd.service.FeeService;
import team.asd.util.ConverterUtil;

import javax.validation.Valid;

@RestController
@RequestMapping(path = {"/fee"})
public class FeeController {
	private final FeeService feeService;

	public FeeController(@Autowired FeeService feeService) {
		this.feeService = feeService;
	}

	@GetMapping(path = {"/{id}"})
	public FeeDto getFeeById(@PathVariable Integer id) {
		return ConverterUtil.convertFeeToDto(feeService.readById(id));
	}

	@PostMapping(path = {"/"})
	public FeeDto createPrice(@RequestBody @Valid FeeDto feeDto) {
		return ConverterUtil.convertFeeToDto(feeService.createFee(ConverterUtil.convertDtoToFee(feeDto)));
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
