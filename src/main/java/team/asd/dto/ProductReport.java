package team.asd.dto;

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

	private ProductDto productDto;

	private List<HalfPrice> halfPrices;

	private List<HalfFee> halfFees;

	private Integer priceCount;

	private Integer feeCount;
}
