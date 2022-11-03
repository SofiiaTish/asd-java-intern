package team.asd.entity;

import team.asd.constant.ProductState;

public class Product {

    private Integer productId;
    private Integer supplierId; //relation to party record
    private String name;
    private ProductState state;
    private String currency;
    private Integer guestsNumber;
    private Double longitude;
    private Double latitude;
    private String physicalAddress;

    public Product() {
    }

    public Product(Integer supplierId, String name, ProductState state, String currency, Integer guestsNumber, Double longitude, Double latitude, String physicalAddress) {
        this.supplierId = supplierId;
        this.name = name;
        this.state = state;
        this.currency = currency;
        this.guestsNumber = guestsNumber;
        this.longitude = longitude;
        this.latitude = latitude;
        this.physicalAddress = physicalAddress;
    }

    public Product(Integer productId, Integer supplierId, String name, ProductState state, String currency, Integer guestsNumber, Double longitude, Double latitude, String physicalAddress) {
        this.productId = productId;
        this.supplierId = supplierId;
        this.name = name;
        this.state = state;
        this.currency = currency;
        this.guestsNumber = guestsNumber;
        this.longitude = longitude;
        this.latitude = latitude;
        this.physicalAddress = physicalAddress;
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

    public ProductState getState() {
        return state;
    }

    public void setState(ProductState state) {
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
}
