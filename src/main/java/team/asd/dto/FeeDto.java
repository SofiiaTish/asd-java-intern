package team.asd.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FeeDto {

	@Positive(message = "{id.positive}")
	@JsonProperty("id")
	private Integer id;

	@Size(max = 15, message = "{type.length}")
	@JsonProperty("fee_type")
	private String feeType;

	@Positive(message = "{id.positive}")
	@JsonProperty("product_id")
	private Integer productId;

	@Size(max = 15, message = "{name.length}")
	@JsonProperty("name")
	private String name;

	@Size(max = 15, message = "{state.length}")
	@JsonProperty("state")
	private String state;

	@Pattern(regexp = "[0-9]{4}-[0-2]{2}-[0-9]{2}", message = "{date.format}")
	@JsonProperty("from_date")
	private String fromDate;

	@Pattern(regexp = "[0-9]{4}-[0-2]{2}-[0-9]{2}", message = "{date.format}")
	@JsonProperty("to_date")
	private String toDate;

	@Size(max = 15, message = "{type.length}")
	@JsonProperty("tax_type")
	private String taxType;

	@Size(max = 15, message = "{unit.length}")
	@JsonProperty("unit")
	private String unit;

	@JsonProperty("value")
	private Double value;

	@Size(max = 15, message = "{type.length}")
	@JsonProperty("value_type")
	private String valueType;

	@Size(max = 3, message = "{currency.length}")
	@JsonProperty("currency")
	private String currency;
}
