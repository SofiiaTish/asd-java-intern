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

	@Positive(message = "Id can`t be less then 1")
	@JsonProperty("id")
	private Integer id;

	@Positive(message = "Id can`t be less then 1")
	@JsonProperty("supplier_id")
	private Integer supplierId; //relation to party record

	@Size(max = 45, message = "Product name can`t have length over 45 symbols")
	@JsonProperty("name")
	private String name;

	@Size(max = 15, message = "Product state can`t have length over 15 symbols")
	@JsonProperty("state")
	private String state;

	@Size(max = 3, message = "Product currency can`t have length over 3 symbols")
	@JsonProperty("currency")
	private String currency;

	@Positive(message = "Number of guests have to be less then 0")
	@JsonProperty("guests_number")
	private Integer guestsNumber;

	@JsonProperty("longitude")
	private Double longitude;

	@JsonProperty("latitude")
	private Double latitude;

	@Size(max = 255, message = "Address can`t have length over 255 symbols")
	@JsonProperty("physical_address")
	private String physicalAddress;

}
