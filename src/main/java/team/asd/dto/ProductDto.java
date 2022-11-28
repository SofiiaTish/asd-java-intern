package team.asd.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDto {

	@Positive(message = "{id.positive}")
	@JsonProperty("id")
	private Integer id;

	@Positive(message = "{id.positive}")
	@JsonProperty("supplier_id")
	private Integer supplierId; //relation to party record

	@Size(max = 45, message = "{name.length}")
	@JsonProperty("name")
	private String name;

	@Size(max = 15, message = "{state.length}")
	@JsonProperty("state")
	private String state;

	@Size(max = 3, message = "{currency.length}")
	@JsonProperty("currency")
	private String currency;

	@Positive(message = "{guests.number}")
	@JsonProperty("guests_number")
	private Integer guestsNumber;

	@JsonProperty("longitude")
	private Double longitude;

	@JsonProperty("latitude")
	private Double latitude;

	@Size(max = 255, message = "{address.length}")
	@JsonProperty("physical_address")
	private String physicalAddress;

}
