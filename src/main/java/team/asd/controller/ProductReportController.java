package team.asd.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.asd.dto.ProductReport;
import team.asd.service.ProductService;

@RestController
@RequestMapping(path = {"/report"})
@ApiOperation("Product Report Api")
public class ProductReportController {

	private final ProductService productService;

	public ProductReportController(ProductService productService) {
		this.productService = productService;
	}

	@ApiOperation(value = "Get product report by id", notes = "Require integer path variable. Return product report object by id as JSON object")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Product report was got"),
			@ApiResponse(code = 404, message = "Custom message about error reasons or unknown error")
	})
	@GetMapping(path = {"/{id}"})
	public ProductReport getProductReportById(@PathVariable @ApiParam(value = "Product Id", example = "4") Integer id) {
		return productService.readProductReportById(id);
	}
}
