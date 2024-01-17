package dto.tm;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.scene.control.Button;
import lombok.*;

@NoArgsConstructor
@ToString
@Setter
@Getter

public class CustomerTm extends RecursiveTreeObject<CustomerTm> {
    private String id;
    private String Name;
    private String Email;
    private String ContactNumber;
    private String Address;
    private Button btn;

    public CustomerTm(String id, String name, String email, String contactNumber, String address, Button btn) {
        this.id = id;
        this.Name = name;
        this.Email = email;
        this.ContactNumber = contactNumber;
        this.Address = address;
        this.btn = btn;
    }
}
