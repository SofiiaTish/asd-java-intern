package team.asd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import team.asd.dto.PriceDto;
import team.asd.service.PriceService;
import team.asd.util.ConverterUtil;

import javax.validation.Valid;

@RestController
@RequestMapping(path = {"/price"})
public class PriceController {
	private final PriceService priceService;

	public PriceController(@Autowired PriceService priceService) {
		this.priceService = priceService;
	}

	@GetMapping(path = {"/{id}"})
	public PriceDto getPriceById(@PathVariable Integer id) {
		return ConverterUtil.convertPriceToDto(priceService.readById(id));
	}

	@PostMapping(path = {"/"})
	public PriceDto createPrPrice(@RequestBody @Valid PriceDto priceDto) {
		return ConverterUtil.convertPriceToDto(priceService.createPrice(ConverterUtil.convertDtoToPrice(priceDto)));
	}

	@PutMapping(path = {"/"})
	public PriceDto updatePrPrice(@RequestBody @Valid PriceDto priceDto) {
		return ConverterUtil.convertPriceToDto(priceService.updatePrice(ConverterUtil.convertDtoToPrice(priceDto)));
	}

	@DeleteMapping(path = {"/{id}"})
	public Integer deletePrPriceById(@PathVariable Integer id) {
		return priceService.deletePrice(id);
	}
}

