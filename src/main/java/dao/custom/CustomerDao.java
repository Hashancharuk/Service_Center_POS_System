package dao.custom;

import dao.CrudDao;
import dto.CustomerDto;
import entity.Customer;

import java.sql.SQLException;

public interface CustomerDao extends CrudDao<Customer>{
    CustomerDto lastOrder() throws SQLException, ClassNotFoundException;
}
