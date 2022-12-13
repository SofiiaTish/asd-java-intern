package team.asd.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import team.asd.constant.FeeType;
import team.asd.constant.TaxType;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HalfFee {

	private String name;

	private Date fromDate;

	private Date toDate;

	private Double value;

	private String currency;

	private TaxType taxType;

	private FeeType feeType;
}
