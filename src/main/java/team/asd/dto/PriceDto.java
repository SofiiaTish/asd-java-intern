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

	@Positive(message = "{id.positive}")
	@JsonProperty("id")
	private Integer id;

	@Size(max = 15, message = "{entity.length}")
	@JsonProperty("entity_type")
	private String entityType;

	@Positive(message = "{id.positive}")
	@JsonProperty("entity_id")
	private Integer entityId;

	@Size(max = 45, message = "{name.length}")
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

	@JsonProperty("value")
	private Double value;

	@Size(max = 3, message = "{currency.length}")
	@JsonProperty("currency")
	private String currency;
}
