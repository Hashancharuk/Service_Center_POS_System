package bo.custom;

import bo.SuperBo;
import dto.CustomerDto;
import dto.EmployeeDto;

import java.sql.SQLException;
import java.util.List;

public interface CustomerBo extends SuperBo {
    boolean saveCustomer(CustomerDto dto)throws SQLException, ClassNotFoundException;
    boolean updateCustomer(CustomerDto dto)throws SQLException, ClassNotFoundException;
    boolean deleteCustomer(String id)throws SQLException, ClassNotFoundException;
    List<CustomerDto> allCustomers()throws SQLException, ClassNotFoundException;
//    CustomerDto lastOrder()throws SQLException, ClassNotFoundException;
}
