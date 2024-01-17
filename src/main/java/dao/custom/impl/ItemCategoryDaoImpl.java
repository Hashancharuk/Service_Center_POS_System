package dao.custom.impl;

import dao.custom.ItemCategoryDao;
import dao.util.HibernateUtil;
import entity.Customer;
import entity.ItemCategory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.List;

public class ItemCategoryDaoImpl implements ItemCategoryDao{


    @Override
    public boolean save(ItemCategory entity) throws SQLException, ClassNotFoundException {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();

        session.save(entity);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(ItemCategory entity) throws SQLException, ClassNotFoundException {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        ItemCategory itemCategory = session.find(ItemCategory.class, entity.getId());
        itemCategory.setName(entity.getName());
        itemCategory.setCategory(entity.getCategory());
        session.save(itemCategory);
        transaction.commit();
        return true;
    }

    @Override
    public boolean delete(String value) throws SQLException, ClassNotFoundException {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        session.delete(session.find(ItemCategory.class,value));
        transaction.commit();
        return true;
    }

    @Override
    public List<ItemCategory> getAll() throws SQLException, ClassNotFoundException {
        Session session = HibernateUtil.getSession();
        Query query = session.createQuery("FROM ItemCategory");
        List<ItemCategory> list = query.list();
        return list;
    }

}
