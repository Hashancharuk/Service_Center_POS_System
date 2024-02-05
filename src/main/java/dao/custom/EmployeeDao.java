package dao.custom;

import dao.CrudDao;
import dto.EmployeeDto;
import entity.Employee;

public interface EmployeeDao extends CrudDao<Employee> {
    EmployeeDto lastEmployee();
}
