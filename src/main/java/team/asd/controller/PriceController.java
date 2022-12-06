package team.asd.controller;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import team.asd.dto.PriceDto;
import team.asd.exception.ValidationException;
import team.asd.service.PriceService;
import team.asd.util.ConverterUtil;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = {"/price"})
@Validated
public class PriceController {
	private final PriceService priceService;

	public PriceController(@Autowired PriceService priceService) {
		this.priceService = priceService;
	}

	@GetMapping(path = {"/{id}"})
	public PriceDto getPriceById(@PathVariable Integer id) {
		return ConverterUtil.convertPriceToDto(priceService.readById(id));
	}

	@GetMapping(path = {"/prices"})
	public List<PriceDto> getPricesByEntityTypeEntityIdState(
			@RequestParam(required = false) String entityType,
			@RequestParam Integer entityId,
			@RequestParam(required = false) String state) {
		return priceService.readByParams(entityType, entityId, state).stream().map(ConverterUtil::convertPriceToDto).collect(Collectors.toList());
	}

	@GetMapping(path = {"/prices/date-range"})
	public List<PriceDto> getPricesByDateRange(
			@RequestParam @Pattern(regexp = "[0-9]{4}-[0-2]{2}-[0-9]{2}", message = "{date.format}") String fromDate,
			@RequestParam @Pattern(regexp = "[0-9]{4}-[0-2]{2}-[0-9]{2}", message = "{date.format}") String toDate) {
		return priceService.readByDateRange(ConverterUtil.convertStringToDate(fromDate), ConverterUtil.convertStringToDate(toDate))
				.stream().map(ConverterUtil::convertPriceToDto).collect(Collectors.toList());
	}

	@PostMapping(path = {"/"})
	public PriceDto createPrice(@RequestBody @Valid PriceDto priceDto) {
		return ConverterUtil.convertPriceToDto(priceService.createPrice(ConverterUtil.convertDtoToPrice(priceDto)));
	}

	@PostMapping(path = {"/prices"})
	public void storePrices(@RequestBody List<@Valid PriceDto> priceDtoList) {
		if (CollectionUtils.isEmpty(priceDtoList)) {
			throw new ValidationException("List of prices is empty");
		} else {
			priceService.createPrices(priceDtoList.stream().map(ConverterUtil::convertDtoToPrice).collect(Collectors.toList()));
		}
	}

	@PutMapping(path = {"/"})
	public PriceDto updatePrice(@RequestBody @Valid PriceDto priceDto) {
		return ConverterUtil.convertPriceToDto(priceService.updatePrice(ConverterUtil.convertDtoToPrice(priceDto)));
	}

	@DeleteMapping(path = {"/{id}"})
	public void deletePriceById(@PathVariable Integer id) {
		priceService.deletePrice(id);
	}
}

