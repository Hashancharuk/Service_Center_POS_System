package dto.tm;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.scene.control.Button;
import lombok.*;

@NoArgsConstructor
@ToString
@Getter
@Setter

public class EmployeeTm extends RecursiveTreeObject<EmployeeTm> {
    private String empId;
    private String empName;
    private String empEmail;
    private String empPassword;
    private String empContNum;
    private String empAddress;
    private Button btn;

    public EmployeeTm(String empId, String empName, String empEmail,String empPassword, String empContNum, String empAddress, Button btn) {
        this.empId = empId;
        this.empName = empName;
        this.empEmail = empEmail;
        this.empPassword = empPassword;
        this.empContNum = empContNum;
        this.empAddress = empAddress;
        this.btn = btn;
    }
}
