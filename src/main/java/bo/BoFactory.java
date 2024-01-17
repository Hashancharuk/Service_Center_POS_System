package bo;

import bo.custom.impl.CustomerBoImpl;
import bo.custom.impl.EmployeeBoImpl;
import bo.custom.impl.ItemCategoryBoImpl;
import bo.custom.impl.PlaceOrderDetailBoImpl;
import dao.util.BoType;

public class BoFactory {
    private static BoFactory boFactory;

    private BoFactory() {
        // Private constructor to enforce singleton pattern
    }

    public static BoFactory getInstance() {
        return boFactory != null ? boFactory : (boFactory = new BoFactory());
    }

    public <T extends SuperBo> T getBo(BoType type) {
        switch (type) {
            case EMPLOYEE:
                return castToType(new EmployeeBoImpl());
            case CUSTOMER:
                return castToType(new CustomerBoImpl());
            case ITEMCATEGORY:
                return castToType(new ItemCategoryBoImpl());
            case PLACEORDER:
                return castToType(new PlaceOrderDetailBoImpl());
        }
        return null;
    }

    private <T extends SuperBo> T castToType(Object obj) {
        try {
            return (T) obj;
        } catch (ClassCastException e) {
            // Handle or log the exception as needed
            e.printStackTrace();
            return null;
        }
    }
}
