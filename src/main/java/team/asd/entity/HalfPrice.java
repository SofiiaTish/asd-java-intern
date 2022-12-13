package team.asd.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HalfPrice {

	private String name;

	private Date fromDate;

	private Date toDate;

	private Double value;

	private String currency;
}
