package team.asd.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel(value = "FeeModel", description = "Model consists of fields that match to table. Model is used for sending data to FeeApi.")
public class FeeDto {

	@ApiModelProperty(value = "The id of fee", example = "4")
	@Positive(message = "{id.positive}")
	@JsonProperty("id")
	private Integer id;

	@ApiModelProperty(value = "The fee type represents type of fee", example = "General")
	@Size(max = 15, message = "{type.length}")
	@JsonProperty("fee_type")
	private String feeType;

	@ApiModelProperty(value = "The product id of fee that specify product", example = "2")
	@Positive(message = "{id.positive}")
	@JsonProperty("product_id")
	private Integer productId;

	@ApiModelProperty(value = "The name of fee", example = "Name")
	@Size(max = 15, message = "{name.length}")
	@JsonProperty("name")
	private String name;

	@ApiModelProperty(value = "The state of fee", example = "Created")
	@Size(max = 15, message = "{state.length}")
	@JsonProperty("state")
	private String state;

	@ApiModelProperty(value = "The date when period starts", example = "2022-10-10")
	@Pattern(regexp = "[0-9]{4}-[0-2]{2}-[0-9]{2}", message = "{date.format}")
	@JsonProperty("from_date")
	private String fromDate;

	@ApiModelProperty(value = "The date when period ends", example = "2022-10-20")
	@Pattern(regexp = "[0-9]{4}-[0-2]{2}-[0-9]{2}", message = "{date.format}")
	@JsonProperty("to_date")
	private String toDate;

	@ApiModelProperty(value = "The tax type", example = "Taxable")
	@Size(max = 15, message = "{type.length}")
	@JsonProperty("tax_type")
	private String taxType;

	@ApiModelProperty(value = "Unit defines how fee will be applied", example = "Per_Person")
	@Size(max = 15, message = "{unit.length}")
	@JsonProperty("unit")
	private String unit;

	@ApiModelProperty(value = "The fee value", example = "34.95")
	@JsonProperty("value")
	private Double value;

	@ApiModelProperty(value = "The value type", example = "Flat")
	@Size(max = 15, message = "{type.length}")
	@JsonProperty("value_type")
	private String valueType;

	@ApiModelProperty(value = "The currency of fee paying", example = "usd")
	@Size(max = 3, message = "{currency.length}")
	@JsonProperty("currency")
	private String currency;
}
