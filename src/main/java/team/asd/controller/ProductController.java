package team.asd.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
@ApiOperation("Product Api")
public class ProductController {

	private final ProductService productService;

	public ProductController(@Autowired ProductService productService) {
		this.productService = productService;
	}

	@ApiOperation(value = "Get product by id", notes = "Require integer path variable. Return product by id as JSON object")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Product was found"),
			@ApiResponse(code = 404, message = "Custom message about error reasons or unknown error")
	})
	@GetMapping(path = {"/{id}"})
	public ProductDto getProductById(@PathVariable @ApiParam(value = "Product Id", example = "4", required = true) Integer id) {
		return ConverterUtil.convertProductToDto(productService.readById(id));
	}

	@ApiOperation(value = "Get product by supplier id, name and state in different combination",
			notes = "Can require supplier id, name and state as request parameters. Return list of products that match to parameters as JSON objects. If all parameters are absent all products will be in list.")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Products exists in database"),
			@ApiResponse(code = 404, message = "Custom message about error reasons or unknown error")
	})
	@GetMapping(path = {"/products"})
	public List<ProductDto> getProductsBySupplierIdNameState(
			@RequestParam(required = false) @ApiParam(value = "Supplier id", example = "1") Integer supplierId,
			@RequestParam(required = false) @ApiParam(value = "Product name", example = "Name") String name,
			@RequestParam(required = false) @ApiParam(value = "Product state", example = "Created") String state) {
		return productService.readByParams(supplierId, name, state).stream().map(ConverterUtil::convertProductToDto).collect(Collectors.toList());
	}

	@ApiOperation(value = "Add new product to table", notes = "Require JSON object with Product fields. Return created product as JSON object")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successful creation"),
			@ApiResponse(code = 404, message = "Custom message about error reasons or unknown error")
	})
	@PostMapping(path = {"/"})
	public ProductDto createProduct(@RequestBody @ApiParam(name = "productForCreation", value = "Product data") @Valid ProductDto productDto) {
		return ConverterUtil.convertProductToDto(productService.createProduct(ConverterUtil.convertDtoToProduct(productDto)));
	}

	@ApiOperation(value = "Add several new products to table", notes = "Require list of JSON objects with Product fields. Can`t process empty list.")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successful creation"),
			@ApiResponse(code = 404, message = "Custom message about error reasons or unknown error")
	})
	@PostMapping(path = {"/products"})
	public void storeProducts(@RequestBody @ApiParam(name = "products", value = "List of products to store") List<@Valid ProductDto> productDtoList) {
		if (CollectionUtils.isEmpty(productDtoList)) {
			throw new ValidationException("List of products is empty");
		} else {
			productService.createProducts(productDtoList.stream().map(ConverterUtil::convertDtoToProduct).collect(Collectors.toList()));
		}
	}

	@ApiOperation(value = "Update product by id", notes = "Require product data to update. Update not null fields. Return updated product as JSON object")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successful update"),
			@ApiResponse(code = 404, message = "Custom message about error reasons or unknown error")
	})
	@PutMapping(path = {"/"})
	public ProductDto updateProduct(@RequestBody @ApiParam(name = "productForUpdate", value = "Product data") @Valid ProductDto productDto) {
		return ConverterUtil.convertProductToDto(productService.updateProduct(ConverterUtil.convertDtoToProduct(productDto)));
	}

	@ApiOperation(value = "Delete product by id", notes = "Require integer path variable. Update column state in table on 'Final'")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successful deleting"),
			@ApiResponse(code = 404, message = "Custom message about error reasons or unknown error")
	})
	@DeleteMapping(path = {"/{id}"})
	public void deleteProductById(@PathVariable @ApiParam(value = "Product Id", example = "4") Integer id) {
		productService.deleteProduct(id);
	}
}
