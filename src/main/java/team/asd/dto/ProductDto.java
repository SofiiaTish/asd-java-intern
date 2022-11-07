package team.asd.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ProductDto {
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("supplier_id")
    private Integer supplierId; //relation to party record
    @JsonProperty("name")
    private String name;
    @JsonProperty("state")
    private String state;
    @JsonProperty("currency")
    private String currency;
    @JsonProperty("guests_number")
    private Integer guestsNumber;
    @JsonProperty("longitude")
    private Double longitude;
    @JsonProperty("latitude")
    private Double latitude;
    @JsonProperty("physical_address")
    private String physicalAddress;
    @JsonProperty("version")
    private String version;

    public ProductDto() {
    }

}
