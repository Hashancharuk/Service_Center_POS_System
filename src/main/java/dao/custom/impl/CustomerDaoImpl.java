package dao.custom.impl;

import dao.custom.CustomerDao;
import dao.util.HibernateUtil;
import dto.CustomerDto;
import entity.Customer;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.sql.SQLException;
import java.util.List;

public class CustomerDaoImpl implements CustomerDao {
    @Override
    public boolean save(Customer entity) throws SQLException, ClassNotFoundException {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        session.save(entity);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Customer entity) throws SQLException, ClassNotFoundException {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        Customer customer = session.find(Customer.class,entity.getCustId());
        customer.setName(entity.getName());
        customer.setAddress(entity.getAddress());
        customer.setEmail(entity.getEmail());
        customer.setContactNumber(entity.getContactNumber());
        customer.setAddress(entity.getAddress());
        session.save(customer);
        transaction.commit();
        return true;
    }

    @Override
    public boolean delete(String value) throws SQLException, ClassNotFoundException {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        session.delete(session.find(Customer.class,value));
        transaction.commit();
        return true;
    }

    @Override
    public List<Customer> getAll() throws SQLException, ClassNotFoundException {
        Session session = HibernateUtil.getSession();
        Query query = session.createQuery("FROM Customer");
        List<Customer> list = query.list();
        return list;
    }

    public CustomerDto lastOrder() {
        Session session = HibernateUtil.getSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<CustomerDto> query = builder.createQuery(CustomerDto.class);

        Root<Customer> customerRoot = query.from(Customer.class);
        query.select(builder.construct(CustomerDto.class,
                customerRoot.get("custId"),
                customerRoot.get("Name"),
                customerRoot.get("Email"),
                customerRoot.get("ContactNumber"),
                customerRoot.get("Address")
        ));
        query.orderBy(builder.desc(customerRoot.get("custId")));

        TypedQuery<CustomerDto> typedQuery = session.createQuery(query);
        typedQuery.setMaxResults(1);

        return ((Query<CustomerDto>) typedQuery).uniqueResult();
    }

}
