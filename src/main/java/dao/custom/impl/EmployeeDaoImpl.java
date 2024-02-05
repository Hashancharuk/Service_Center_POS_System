package dao.custom.impl;

import dao.custom.EmployeeDao;
import dao.util.HibernateUtil;
import dto.CustomerDto;
import dto.EmployeeDto;
import entity.Customer;
import entity.Employee;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.sql.SQLException;
import java.util.List;

public class EmployeeDaoImpl implements EmployeeDao {
    @Override
    public boolean save(Employee entity) throws SQLException, ClassNotFoundException {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        session.save(entity);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Employee entity) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();

        try {
            // Retrieve the existing employee from the session
            Employee employee = session.get(Employee.class, entity.getEmployee_ID());

            // Check if the employee exists
            if (employee != null) {
                // Update the employee details
                employee.setName(entity.getName());
                employee.setEmail(entity.getEmail());
//                employee.setPassword(entity.getPassword());
                employee.setContactNumber(entity.getContactNumber());
                employee.setAddress(entity.getAddress());

                // Save the updated employee back to the database
                session.update(employee);

                transaction.commit();
                return true;
            } else {
                transaction.rollback();
                return false; // Or throw an exception indicating that the employee does not exist
            }
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace(); // Log the exception
            return false; // Or rethrow the exception
        } finally {
            session.close();
        }
    }


    @Override
    public boolean delete(String value) throws SQLException, ClassNotFoundException {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        session.delete(session.find(Employee.class,value));
        transaction.commit();
        return true;
    }

    @Override
    public List<Employee> getAll() throws SQLException, ClassNotFoundException {
        Session session = HibernateUtil.getSession();
        Query query = session.createQuery("FROM Employee");
        List<Employee> list = query.list();
        return list;
    }

    @Override
    public EmployeeDto lastEmployee() {
        Session session = HibernateUtil.getSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<EmployeeDto> query = builder.createQuery(EmployeeDto.class);

        Root<Employee> employeeRoot = query.from(Employee.class);
        query.select(builder.construct(EmployeeDto.class,
                employeeRoot.get("Employee_ID"),
                employeeRoot.get("Name"),
                employeeRoot.get("Email"),
                employeeRoot.get("Password"),
                employeeRoot.get("ContactNumber"),
                employeeRoot.get("Address")
        ));
        query.orderBy(builder.desc(employeeRoot.get("Employee_ID")));

        TypedQuery<EmployeeDto> typedQuery = session.createQuery(query);
        typedQuery.setMaxResults(1);

        return ((Query<EmployeeDto>) typedQuery).uniqueResult();
    }

}
