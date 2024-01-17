package dao.custom.impl;

import dao.custom.PlaceOrderDetailDao;
import dao.util.HibernateUtil;
import entity.PlaceOrderDetail;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlaceOrderDetailDaoImpl implements PlaceOrderDetailDao {
    @Override
    public boolean save(PlaceOrderDetail entity) throws SQLException, ClassNotFoundException {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();

        session.save(entity);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(PlaceOrderDetail entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public List<PlaceOrderDetail> getAll() throws SQLException, ClassNotFoundException {
       Session session = HibernateUtil.getSession();
        Query query = session.createQuery("FROM PlaceOrderDetail");
        List<PlaceOrderDetail> list = query.list();
        return list;
    }
}
