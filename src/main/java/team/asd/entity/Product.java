package team.asd.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import team.asd.constant.ProductState;

import java.util.Date;


@Data
@AllArgsConstructor
@Builder
public class Product {

	private Integer id;
	private Integer supplierId; //relation to party record
	private String name;
	private ProductState state;
	private String currency;
	private Integer guestsNumber;
	private Double longitude;
	private Double latitude;
	private String physicalAddress;

	private Date version;

	public Product() {
	}
	
}

