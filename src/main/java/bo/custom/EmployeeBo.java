package bo.custom;

import bo.SuperBo;
import dto.CustomerDto;
import dto.EmployeeDto;

import java.sql.SQLException;
import java.util.List;

public interface  EmployeeBo extends SuperBo {
    boolean saveEmployee(EmployeeDto dto)throws SQLException, ClassNotFoundException;
    boolean updateEmployee(EmployeeDto dto)throws SQLException, ClassNotFoundException;
    boolean deleteEmployee(String empId)throws SQLException, ClassNotFoundException;
    List<EmployeeDto> allEmployees()throws SQLException, ClassNotFoundException;

    EmployeeDto lastEmployee()throws SQLException, ClassNotFoundException;
}
