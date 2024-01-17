package bo.custom.impl;

import bo.custom.EmployeeBo;
import dao.DaoFactory;
import dao.custom.EmployeeDao;
import dao.util.DaoType;
import dto.EmployeeDto;
import entity.Employee;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class EmployeeBoImpl implements EmployeeBo {
    private EmployeeDao employeeDao = DaoFactory.getInstance().getDao(DaoType.EMPLOYEE);
    @Override
    public boolean saveEmployee(EmployeeDto dto) throws SQLException, ClassNotFoundException {
        return employeeDao.save(new Employee(
                dto.getEmployeeId(),
                dto.getEmployeeName(),
                dto.getEmployeeEmail(),
                dto.getEmployeePassword(),
                dto.getEmployeeContNumber(),
                dto.getEmployeeAddress()
        ));
    }
    public boolean updateEmployee(EmployeeDto dto) throws SQLException, ClassNotFoundException {
        return employeeDao.update(new Employee(
                        dto.getEmployeeId(),
                        dto.getEmployeeName(),
                        dto.getEmployeeEmail(),
                        dto.getEmployeePassword(),
                        dto.getEmployeeContNumber(),
                        dto.getEmployeeAddress()
        ));
    }

    @Override
    public boolean deleteEmployee(String empId) throws SQLException, ClassNotFoundException {
        return employeeDao.delete(empId);
    }

    @Override
    public List<EmployeeDto> allEmployees() throws SQLException, ClassNotFoundException {
        List<Employee> entityList = employeeDao.getAll();
        List<EmployeeDto> list = new ArrayList<>();
        for (Employee employee : entityList) {
            list.add(new EmployeeDto(
                    employee.getEmployee_ID(),
                    employee.getName(),
                    employee.getEmail(),
                    employee.getPassword(),
                    employee.getContactNumber(),
                    employee.getAddress()

            ));
        }
        return list;
    }
}
