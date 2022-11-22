package team.asd.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
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
public class PriceDto {

	@Positive(message = "Id can`t be less then 1")
	@JsonProperty("id")
	private Integer id;

	@Size(max = 15, message = "Price entity can`t have length over 15 symbols")
	@JsonProperty("entity_type")
	private String entityType;

	@Positive(message = "Id can`t be less then 1")
	@JsonProperty("entity_id")
	private Integer entityId;

	@Size(max = 45, message = "Price name can`t have length over 45 symbols")
	@JsonProperty("name")
	private String name;

	@Size(max = 15, message = "Price state can`t have length over 15 symbols")
	@JsonProperty("state")
	private String state;

	@Pattern(regexp = "[0-9]{4}-[0-2]{2}-[0-9]{2}", message = "Incorrect format of date")
	@JsonProperty("from_date")
	private String fromDate;

	@Pattern(regexp = "[0-9]{4}-[0-2]{2}-[0-9]{2}", message = "Incorrect format of date")
	@JsonProperty("to_date")
	private String toDate;

	@JsonProperty("value")
	private Double value;

	@Size(max = 3, message = "Price currency can`t have length over 3 symbols")
	@JsonProperty("currency")
	private String currency;
}
