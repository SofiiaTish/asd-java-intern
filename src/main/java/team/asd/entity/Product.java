package team.asd.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import team.asd.constant.ProductState;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
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
    private LocalDateTime version;

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


}
