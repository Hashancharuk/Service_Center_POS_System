package dao.custom.impl;

import dao.custom.EmployeeDao;
import dao.util.HibernateUtil;
import entity.Employee;
import org.hibernate.Session;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

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
}
