package entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Entity
public class Employee {
    @Id
    private String Employee_ID;
    private String Name;
    private String Email;
    private String Password;
    private String ContactNumber;
    private String Address;
}
