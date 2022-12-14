package team.asd.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import team.asd.entity.HalfFee;
import team.asd.entity.HalfPrice;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductReport {

	@JsonProperty("product_id")
	private Integer id;

	@JsonProperty("product_dto")
	private ProductDto productDto;

	@JsonProperty("price_list")
	private List<HalfPrice> halfPrices;

	@JsonProperty("fee_list")
	private List<HalfFee> halfFees;

	@JsonProperty("price_count")
	private Integer priceCount;

	@JsonProperty("fee_count")
	private Integer feeCount;
}
