package team.asd.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FeeDto {

	@Positive(message = "Id can`t be less then 1")
	@JsonProperty("id")
	private Integer id;

	@Size(max = 15, message = "Fee type can`t have length over 15 symbols")
	@JsonProperty("fee_type")
	private String feeType;

	@Positive(message = "Id can`t be less then 1")
	@JsonProperty("product_id")
	private Integer productId;

	@Size(max = 15, message = "Fee name can`t have length over 15 symbols")
	@JsonProperty("name")
	private String name;

	@Size(max = 15, message = "Fee state can`t have length over 15 symbols")
	@JsonProperty("state")
	private String state;

	@Pattern(regexp = "[0-9]{4}-[0-2]{2}-[0-9]{2}", message = "Incorrect format of date")
	@JsonProperty("from_date")
	private String fromDate;

	@Pattern(regexp = "[0-9]{4}-[0-2]{2}-[0-9]{2}", message = "Incorrect format of date")
	@JsonProperty("to_date")
	private String toDate;

	@Size(max = 15, message = "Tax type can`t have length over 15 symbols")
	@JsonProperty("tax_type")
	private String taxType;

	@Size(max = 15, message = "Unit can`t have length over 15 symbols")
	@JsonProperty("unit")
	private String unit;

	@JsonProperty("value")
	private Double value;

	@Size(max = 15, message = "Value type can`t have length over 15 symbols")
	@JsonProperty("value_type")
	private String valueType;

	@Size(max = 3, message = "Fee currency can`t have length over 3 symbols")
	@JsonProperty("currency")
	private String currency;
}
