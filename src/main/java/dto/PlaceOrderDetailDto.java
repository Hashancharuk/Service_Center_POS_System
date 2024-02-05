package dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class PlaceOrderDetailDto {
    private String orderId;
    private String custId;
    private String name;
    private String itemCategory;
    private String itemName;
    private String date;
    private int qty;
    private String fault;

    public String getId() {
        return orderId;
    }
}
