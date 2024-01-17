package dao;

import dao.custom.impl.CustomerDaoImpl;
import dao.custom.impl.EmployeeDaoImpl;
import dao.custom.impl.ItemCategoryDaoImpl;
import dao.custom.impl.PlaceOrderDetailDaoImpl;
import dao.util.DaoType;

public class DaoFactory {
    private static DaoFactory daoFactory;
    private DaoFactory(){

    }
    public static DaoFactory getInstance() {

        return daoFactory != null? daoFactory:(daoFactory=new DaoFactory());
    }
    public <T extends SuperDao>T getDao(DaoType type){
        switch (type){
            case EMPLOYEE:return (T) new EmployeeDaoImpl();
            case CUSTOMER : return (T) new CustomerDaoImpl();
            case ITEMCATEGORY:return (T) new ItemCategoryDaoImpl();
            case PLACEORDER:return (T) new PlaceOrderDetailDaoImpl();
        }
        return null;
    }
}
