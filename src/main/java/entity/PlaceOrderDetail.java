package entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@NoArgsConstructor
@Setter
@Getter
@ToString
@Entity
@AllArgsConstructor

public class PlaceOrderDetail {
    @Id
    private String orderId;
    private String email;
    private String contactNumber;
    private String name;
    private String itemCategory;
    private String itemName;
    private String date;
    private int qty;
    private String fault;

//    public PlaceOrderDetail(String orderId, String custId, String name, String itemCategory, String itemName, String date, int qty, String fault) {
//        this.orderId = orderId;
//        this.custId = custId;
//        this.name = name;
//        this.itemCategory = itemCategory;
//        this.itemName = itemName;
//        this.date = date;
//        this.qty = qty;
//        this.fault = fault;
//    }
}
