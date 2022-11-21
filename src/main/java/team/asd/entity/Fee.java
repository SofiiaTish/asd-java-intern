package team.asd.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import team.asd.constant.FeeState;
import team.asd.constant.FeeType;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Fee {

	private Integer id;

	private FeeType feeType;

	private Integer productId;

	private String name;

	private FeeState state;

	private Date fromDate;

	private Date toDate;

	private Integer taxType;

	private Integer unit;

	private Double value;

	private Integer valueType;

	private String currency;

	private Date version;
}
