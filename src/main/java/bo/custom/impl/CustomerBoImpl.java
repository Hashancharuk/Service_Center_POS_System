package bo.custom.impl;

import bo.custom.CustomerBo;
import dao.DaoFactory;
import dao.custom.CustomerDao;
import dao.util.DaoType;
import dto.CustomerDto;
import entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerBoImpl implements CustomerBo {

    private CustomerDao customerDao;
    public CustomerBoImpl() {

        this.customerDao=DaoFactory.getInstance().getDao(DaoType.CUSTOMER);
    }
    @Override
    public boolean saveCustomer(CustomerDto dto) throws SQLException, ClassNotFoundException {
        return customerDao.save(new Customer(
                dto.getId(),
                dto.getName(),
                dto.getEmail(),
                dto.getContactNumber(),
                dto.getAddress()
        ));
    }

    @Override
    public boolean updateCustomer(CustomerDto dto) throws SQLException, ClassNotFoundException {
        return customerDao.update(new Customer(
                dto.getId(),
                dto.getName(),
                dto.getEmail(),
                dto.getContactNumber(),
                dto.getAddress()
        ));
    }

    @Override
    public boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException {
        return customerDao.delete(id);
    }

    @Override
    public List<CustomerDto> allCustomers() throws SQLException, ClassNotFoundException {
        List<Customer> entityList = customerDao.getAll();
        List<CustomerDto> list = new ArrayList<>();
        for (Customer customer:entityList) {
            list.add(new CustomerDto(
                    customer.getCustId(),
                    customer.getName(),
                    customer.getEmail(),
                    customer.getContactNumber(),
                    customer.getAddress()
            ));
        }
        return list;
    }

    @Override
    public CustomerDto lastOrder() throws SQLException, ClassNotFoundException {
        return customerDao.lastOrder();
    }


}
