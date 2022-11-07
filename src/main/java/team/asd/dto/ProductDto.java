package team.asd.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;

public class ProductDto {
    @JsonProperty("product_id")
    private Integer productId;
    private Integer supplierId; //relation to party record
    private String name;
    private String state;
    private String currency;
    private Integer guestsNumber;
    private Double longitude;
    private Double latitude;
    private String physicalAddress;
    @JsonProperty("version")
    private String version;

    public ProductDto() {
    }

    public ProductDto(Integer productId, Integer supplierId, String name, String state, String currency, Integer guestsNumber, Double longitude, Double latitude, String physicalAddress, String version) {
        this.productId = productId;
        this.supplierId = supplierId;
        this.name = name;
        this.state = state;
        this.currency = currency;
        this.guestsNumber = guestsNumber;
        this.longitude = longitude;
        this.latitude = latitude;
        this.physicalAddress = physicalAddress;
        this.version = version;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Integer getGuestsNumber() {
        return guestsNumber;
    }

    public void setGuestsNumber(Integer guestsNumber) {
        this.guestsNumber = guestsNumber;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public String getPhysicalAddress() {
        return physicalAddress;
    }

    public void setPhysicalAddress(String physicalAddress) {
        this.physicalAddress = physicalAddress;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
