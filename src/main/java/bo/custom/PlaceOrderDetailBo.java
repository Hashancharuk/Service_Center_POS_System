package bo.custom;

import bo.SuperBo;
import dto.PlaceOrderDetailDto;

import java.sql.SQLException;
import java.util.List;

public interface PlaceOrderDetailBo extends SuperBo {
    boolean saveOrder(PlaceOrderDetailDto dto)throws SQLException, ClassNotFoundException;
    boolean updateOrder(PlaceOrderDetailDto dto)throws SQLException, ClassNotFoundException;
    boolean deleteOrder(String id)throws SQLException, ClassNotFoundException;
    List<PlaceOrderDetailDto> allPlaceOrders() throws SQLException, ClassNotFoundException;

    PlaceOrderDetailDto lastOrderCode()throws SQLException, ClassNotFoundException;
}
