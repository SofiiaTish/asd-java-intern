package team.asd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.asd.dto.ProductDto;
import team.asd.service.ProductService;
import team.asd.util.ConverterUtil;

import javax.validation.Valid;

@RestController
@RequestMapping(path = { "/product" })
public class ProductController {

	private final ProductService productService;

	public ProductController(@Autowired ProductService productService) {
		this.productService = productService;
	}

	@GetMapping(path = { "/{id}" })
	public ProductDto getProductById(@PathVariable Integer id) {
		return ConverterUtil.convertProductToDto(productService.readById(id));
	}

	@PostMapping(path = { "/" })
	public ProductDto createProduct(@RequestBody @Valid ProductDto productDto) {
		return ConverterUtil.convertProductToDto(productService.createProduct(ConverterUtil.convertDtoToProduct(productDto)));
	}

	@PutMapping(path = { "/" })
	public ProductDto updateProduct(@RequestBody @Valid ProductDto productDto) {
		return ConverterUtil.convertProductToDto(productService.updateProduct(ConverterUtil.convertDtoToProduct(productDto)));
	}

	@DeleteMapping(path = { "/{id}" })
	public void deleteProductById(@PathVariable Integer id) {
		productService.deleteProduct(id);
	}
}
