package dao.custom;

import dao.CrudDao;
import dto.PlaceOrderDetailDto;
import entity.PlaceOrderDetail;

public interface PlaceOrderDetailDao extends CrudDao<PlaceOrderDetail> {
    PlaceOrderDetailDto lastOrderCode();
}
