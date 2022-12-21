package team.asd.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import team.asd.constant.PriceState;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Price {

	private Integer id;

	private String entityType;

	private Integer entityId;

	private String name;

	private PriceState state;

	private Date fromDate;

	private Date toDate;

	private Double value;

	private String currency;

	private Date version;

}
