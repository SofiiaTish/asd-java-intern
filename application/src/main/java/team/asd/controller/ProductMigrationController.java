package team.asd.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import team.asd.service.ProductMigrationService;
import team.asd.util.ConverterUtil;

import javax.validation.constraints.Pattern;

@RestController
@RequestMapping(path = {"/migration"})
@Validated
@ApiOperation("Product Migration Api")
public class ProductMigrationController {

	private final ProductMigrationService migrationService;

	public ProductMigrationController(ProductMigrationService migrationService) {
		this.migrationService = migrationService;
	}

	@ApiOperation(value = "Get percent of filling data by date range for current product.",
			notes = "Require two different dates and productId as request parameters. Return percent value of all product`s data as string. Parameters have to be in order 'start date, end date'.")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Percent result"),
			@ApiResponse(code = 404, message = "Custom message about error reasons or unknown error")
	})
	@GetMapping(path = {""})
	public String checkProductMigration(
			@RequestParam @ApiParam(value = "Product id", example = "5") Integer productId,
			@RequestParam @ApiParam(value = "Start date of range", example = "2022-12-06")
			@Pattern(regexp = "[0-9]{4}-[0-2]{2}-[0-9]{2}", message = "{date.format}") String fromDate,
			@RequestParam @ApiParam(value = "End date of range", example = "2022-12-24")
			@Pattern(regexp = "[0-9]{4}-[0-2]{2}-[0-9]{2}", message = "{date.format}") String toDate) {
		return migrationService.checkProductMigration(productId, ConverterUtil.convertStringToDate(fromDate), ConverterUtil.convertStringToDate(toDate));
	}
}
