package team.asd.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(value = "PriceModel", description = "Model consists of fields that match to table. Model is used for sending data to PriceApi.")
public class PriceDto {

	@ApiModelProperty(value = "The id of price", example = "4")
	@Positive(message = "{id.positive}")
	@JsonProperty("id")
	private Integer id;

	@ApiModelProperty(value = "The entity type of price that represents type of product", example = "Product")
	@Size(max = 15, message = "{entity.length}")
	@JsonProperty("entity_type")
	private String entityType;

	@ApiModelProperty(value = "The entity id of price that specify product", example = "2")
	@Positive(message = "{id.positive}")
	@JsonProperty("entity_id")
	private Integer entityId;

	@ApiModelProperty(value = "The name of price", example = "Name")
	@Size(max = 45, message = "{name.length}")
	@JsonProperty("name")
	private String name;

	@ApiModelProperty(value = "The state of price", example = "Created")
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

	@ApiModelProperty(value = "The price value", example = "34.95")
	@JsonProperty("value")
	private Double value;

	@ApiModelProperty(value = "The currency of price paying", example = "usd")
	@Size(max = 3, message = "{currency.length}")
	@JsonProperty("currency")
	private String currency;
}
