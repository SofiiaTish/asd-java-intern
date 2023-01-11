package team.asd.controller;

import io.swagger.annotations.ApiOperation;
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

	@GetMapping(path = {""})
	public String checkProductMigration(
			@RequestParam Integer productId,
			@RequestParam @Pattern(regexp = "[0-9]{4}-[0-2]{2}-[0-9]{2}", message = "{date.format}") String fromDate,
			@RequestParam @Pattern(regexp = "[0-9]{4}-[0-2]{2}-[0-9]{2}", message = "{date.format}") String toDate) {
		return migrationService.checkProductMigration(productId, ConverterUtil.convertStringToDate(fromDate), ConverterUtil.convertStringToDate(toDate));
	}
}
