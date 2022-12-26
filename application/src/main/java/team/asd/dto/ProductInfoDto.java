package team.asd.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(value = "ProductInfoModel", description = "Model consists of fields that match to tables product, party and reservation. Model is used for sending data to ProductApi.")
public class ProductInfoDto {

	@ApiModelProperty(value = "The id of product", example = "4")
	@JsonProperty("id")
	private Integer id;

	@ApiModelProperty(value = "The name of product", example = "Name")
	@JsonProperty("product_name")
	private String productName;

	@ApiModelProperty(value = "The state of product", example = "Created")
	@JsonProperty("product_state")
	private String productState;

	@ApiModelProperty(value = "The amount of reservations with product id", example = "3")
	@JsonProperty("reservation_count")
	private Integer reservationCountByProductId;

	@ApiModelProperty(value = "The name of supplier", example = "Supplier")
	@JsonProperty("supplier_name")
	private String supplierName;

	@ApiModelProperty(value = "The user type of supplier", example = "Customer")
	@JsonProperty("user_type")
	private String supplierUserType;
}
