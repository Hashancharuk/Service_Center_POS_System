package dao.custom.impl;

import dao.custom.PlaceOrderDetailDao;
import dao.util.HibernateUtil;
import dto.ItemCategoryDto;
import dto.PlaceOrderDetailDto;
import entity.ItemCategory;
import entity.PlaceOrderDetail;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
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

    @Override
    public PlaceOrderDetailDto lastOrderCode() {
        Session session = HibernateUtil.getSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<PlaceOrderDetailDto> query = builder.createQuery(PlaceOrderDetailDto.class);

        Root<PlaceOrderDetail> placeOrderDetailRoot = query.from(PlaceOrderDetail.class);
        query.select(builder.construct(PlaceOrderDetailDto.class,
                placeOrderDetailRoot.get("orderId"),
                placeOrderDetailRoot.get("custId"),
                placeOrderDetailRoot.get("name"),
                placeOrderDetailRoot.get("itemCategory"),
                placeOrderDetailRoot.get("itemName"),
                placeOrderDetailRoot.get("date"),
                placeOrderDetailRoot.get("qty"),
                placeOrderDetailRoot.get("fault")
        ));
        query.orderBy(builder.desc(placeOrderDetailRoot.get("orderId")));

        TypedQuery<PlaceOrderDetailDto> typedQuery = session.createQuery(query);
        typedQuery.setMaxResults(1);

        return ((Query<PlaceOrderDetailDto>) typedQuery).uniqueResult();
    }
}


