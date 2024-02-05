package dao.custom.impl;

import dao.custom.ItemCategoryDao;
import dao.util.HibernateUtil;
import dto.EmployeeDto;
import dto.ItemCategoryDto;
import entity.Customer;
import entity.Employee;
import entity.ItemCategory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
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

    @Override
    public ItemCategoryDto lastItemCode() {
        Session session = HibernateUtil.getSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<ItemCategoryDto> query = builder.createQuery(ItemCategoryDto.class);

        Root<ItemCategory> itemCategoryRoot = query.from(ItemCategory.class);
        query.select(builder.construct(ItemCategoryDto.class,
                itemCategoryRoot.get("id"),
                itemCategoryRoot.get("name"),
                itemCategoryRoot.get("category")
        ));
        query.orderBy(builder.desc(itemCategoryRoot.get("id")));

        TypedQuery<ItemCategoryDto> typedQuery = session.createQuery(query);
        typedQuery.setMaxResults(1);

        return ((Query<ItemCategoryDto>) typedQuery).uniqueResult();
    }
}
