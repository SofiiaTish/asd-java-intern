package team.asd.controller;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import team.asd.dto.ProductDto;
import team.asd.exception.ValidationException;
import team.asd.service.ProductService;
import team.asd.util.ConverterUtil;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = {"/product"})
@Validated
public class ProductController {

	private final ProductService productService;

	public ProductController(@Autowired ProductService productService) {
		this.productService = productService;
	}

	@GetMapping(path = {"/{id}"})
	public ProductDto getProductById(@PathVariable Integer id) {
		return ConverterUtil.convertProductToDto(productService.readById(id));
	}

	@GetMapping(path = {"/products"})
	public List<ProductDto> getProductsBySupplierIdNameState(
			@RequestParam(required = false) Integer supplierId,
			@RequestParam(required = false) String name,
			@RequestParam(required = false) String state) {
		return productService.readByParams(supplierId, name, state).stream().map(ConverterUtil::convertProductToDto).collect(Collectors.toList());
	}

	@PostMapping(path = {"/"})
	public ProductDto createProduct(@RequestBody @Valid ProductDto productDto) {
		return ConverterUtil.convertProductToDto(productService.createProduct(ConverterUtil.convertDtoToProduct(productDto)));
	}

	@PostMapping(path = {"/products"})
	public void storeProducts(@RequestBody List<@Valid ProductDto> productDtoList) {
		if (CollectionUtils.isEmpty(productDtoList)) {
			throw new ValidationException("List of products is empty");
		} else {
			productService.createProducts(productDtoList.stream().map(ConverterUtil::convertDtoToProduct).collect(Collectors.toList()));
		}
	}

	@PutMapping(path = {"/"})
	public ProductDto updateProduct(@RequestBody @Valid ProductDto productDto) {
		return ConverterUtil.convertProductToDto(productService.updateProduct(ConverterUtil.convertDtoToProduct(productDto)));
	}

	@DeleteMapping(path = {"/{id}"})
	public void deleteProductById(@PathVariable Integer id) {
		productService.deleteProduct(id);
	}
}
