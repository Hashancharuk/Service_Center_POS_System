package entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;
@NoArgsConstructor
@Setter
@Getter
@ToString
@Entity

public class PlaceOrderDetail {
    @Id
    private String orderId;
    private String custId;
    private String name;
    private String itemCategory;
    private String itemName;
    private String date;
    private int qty;
    private String fault;

    public PlaceOrderDetail(String orderId, String custId, String name, String itemCategory, String itemName, String date, int qty, String fault) {
        this.orderId = orderId;
        this.custId = custId;
        this.name = name;
        this.itemCategory = itemCategory;
        this.itemName = itemName;
        this.date = date;
        this.qty = qty;
        this.fault = fault;
    }
}
