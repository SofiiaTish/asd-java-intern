package team.asd.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
@ApiOperation("Price Api")
public class PriceController {
	private final PriceService priceService;

	public PriceController(@Autowired PriceService priceService) {
		this.priceService = priceService;
	}

	@ApiOperation(value = "Get price by id", notes = "Require integer path variable. Return price by id as JSON object")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Price was found"),
			@ApiResponse(code = 404, message = "Custom message about error reasons or unknown error")
	})
	@GetMapping(path = {"/{id}"})
	public PriceDto getPriceById(@PathVariable @ApiParam(value = "Price Id", example = "4") Integer id) {
		return ConverterUtil.convertPriceToDto(priceService.readById(id));
	}

	@ApiOperation(value = "Get prices by entity type, entity id and state in different combination",
			notes = "Can require entity type, entity id and state as request parameters. Return list of prices that match to parameters as JSON objects.")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Prices exist in table"),
			@ApiResponse(code = 404, message = "Custom message about error reasons or unknown error")
	})
	@GetMapping(path = {"/prices"})
	public List<PriceDto> getPricesByEntityTypeEntityIdState(
			@RequestParam(required = false) @ApiParam(value = "Entity type", example = "Product") String entityType,
			@RequestParam @ApiParam(value = "Entity id", example = "2") Integer entityId,
			@RequestParam(required = false) @ApiParam(value = "Product state", example = "Created") String state) {
		return priceService.readByParams(entityType, entityId, state).stream().map(ConverterUtil::convertPriceToDto).collect(Collectors.toList());
	}

	@ApiOperation(value = "Get prices by date range.",
			notes = "Require two different dates as request parameters. Return list of prices witch date ranges are inside specified range as JSON objects. Parameters have to be in order 'start date, end date'.")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Prices exist in table"),
			@ApiResponse(code = 404, message = "Custom message about error reasons or unknown error")
	})
	@GetMapping(path = {"/prices/date-range"})
	public List<PriceDto> getPricesByDateRange(
			@RequestParam @ApiParam(value = "Start date of range", example = "2022-10-10")
			@Pattern(regexp = "[0-9]{4}-[0-2]{2}-[0-9]{2}", message = "{date.format}") String fromDate,
			@RequestParam @ApiParam(value = "End date of range", example = "2022-10-20")
			@Pattern(regexp = "[0-9]{4}-[0-2]{2}-[0-9]{2}", message = "{date.format}") String toDate) {
		return priceService.readByDateRange(ConverterUtil.convertStringToDate(fromDate), ConverterUtil.convertStringToDate(toDate))
				.stream().map(ConverterUtil::convertPriceToDto).collect(Collectors.toList());
	}

	@ApiOperation(value = "Add new price to table", notes = "Require JSON object with Price fields. Return created price as JSON object")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successful creation"),
			@ApiResponse(code = 404, message = "Custom message about error reasons or unknown error")
	})
	@PostMapping(path = {"/"})
	public PriceDto createPrice(@RequestBody @ApiParam(name = "priceForCreation", value = "Price data") @Valid PriceDto priceDto) {
		return ConverterUtil.convertPriceToDto(priceService.createPrice(ConverterUtil.convertDtoToPrice(priceDto)));
	}

	@ApiOperation(value = "Add several new prices to table", notes = "Require list of JSON objects with Price fields. Can`t process empty list.")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successful creation"),
			@ApiResponse(code = 404, message = "Custom message about error reasons or unknown error")
	})
	@PostMapping(path = {"/prices"})
	public void storePrices(@RequestBody @ApiParam(name = "prices", value = "List of prices to store") List<@Valid PriceDto> priceDtoList) {
		if (CollectionUtils.isEmpty(priceDtoList)) {
			throw new ValidationException("List of prices is empty");
		} else {
			priceService.createPrices(priceDtoList.stream().map(ConverterUtil::convertDtoToPrice).collect(Collectors.toList()));
		}
	}

	@ApiOperation(value = "Update price by id", notes = "Require price data to update. Update not null fields. Return updated price as JSON object")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successful update"),
			@ApiResponse(code = 404, message = "Custom message about error reasons or unknown error")
	})
	@PutMapping(path = {"/"})
	public PriceDto updatePrice(@RequestBody @ApiParam(name = "priceForUpdate", value = "Price data") @Valid PriceDto priceDto) {
		return ConverterUtil.convertPriceToDto(priceService.updatePrice(ConverterUtil.convertDtoToPrice(priceDto)));
	}

	@ApiOperation(value = "Delete price by id", notes = "Require integer path variable. Update column state in table on 'Final'")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successful deleting"),
			@ApiResponse(code = 404, message = "Custom message about error reasons or unknown error")
	})
	@DeleteMapping(path = {"/{id}"})
	public void deletePriceById(@PathVariable @ApiParam(value = "Price Id", example = "4") Integer id) {
		priceService.deletePrice(id);
	}
}

