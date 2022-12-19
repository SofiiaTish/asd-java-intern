package team.asd.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductReport {

	private Integer id;

	private Product product;

	private List<HalfPrice> halfPrices;

	private List<HalfFee> halfFees;

	private Integer priceCount;

	private Integer feeCount;
}
