package team.asd.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import team.asd.util.validgroup.OnCreate;
import team.asd.util.validgroup.OnUpdate;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(value = "ProductModel", description = "Model consists of fields that match to table. Model is used for sending data to ProductApi.")
public class ProductDto {

	@ApiModelProperty(value = "The id of product", example = "4")
	@NotNull(message = "OnUpdate Validation: id not null", groups = {OnUpdate.class})
	@Positive(message = "{id.positive}", groups = {OnUpdate.class})
	@Positive(message = "{id.positive}")
	@JsonProperty("id")
	private Integer id;

	@ApiModelProperty(value = "The supplier id of product", example = "2")
	@Positive(message = "{id.positive}")
	@JsonProperty("supplier_id")
	private Integer supplierId; //relation to party record

	@ApiModelProperty(value = "The name of product", example = "Name")
	@Size(max = 45, message = "{name.length}")
	@JsonProperty("name")
	private String name;

	@ApiModelProperty(value = "The state of product", example = "Created")
	@Size(max = 15, message = "{state.length}")
	@JsonProperty("state")
	private String state;

	@ApiModelProperty(value = "The currency of product buying", example = "usd")
	@Size(max = 3, message = "{currency.length}")
	@JsonProperty("currency")
	private String currency;

	@ApiModelProperty(value = "The number of guests that can be connected to product", example = "1")
	@Positive(message = "{guests.number}")
	@JsonProperty("guests_number")
	private Integer guestsNumber;

	@ApiModelProperty(value = "The longitude of product location", example = "23.78")
	@JsonProperty("longitude")
	private Double longitude;

	@ApiModelProperty(value = "The latitude of product location", example = "45.33")
	@JsonProperty("latitude")
	private Double latitude;

	@ApiModelProperty(value = "The address of product location", example = "Current street 5a")
	@Size(max = 255, message = "{address.length}")
	@JsonProperty("physical_address")
	private String physicalAddress;

}
