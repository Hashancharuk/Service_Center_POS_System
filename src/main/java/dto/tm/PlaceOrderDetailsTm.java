package dto.tm;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.scene.control.Button;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Setter
@Getter
@ToString

public class PlaceOrderDetailsTm extends RecursiveTreeObject<PlaceOrderDetailsTm> {
    private String orderId;
    private String custId;
    private String name;
    private String itemCategory;
    private String itemName;
    private String date;
    private String qty;
    private String fault;
    private Button btn;

    public PlaceOrderDetailsTm(String orderId, String custId, String name, String itemCategory, String itemName, String date, int qty, String fault, Button btn) {
        this.orderId = orderId;
        this.custId = custId;
        this.name = name;
        this.itemCategory = itemCategory;
        this.itemName = itemName;
        this.date = String.valueOf(date);
        this.qty = String.valueOf(qty);
        this.fault = fault;
        this.btn = btn;
    }
}
