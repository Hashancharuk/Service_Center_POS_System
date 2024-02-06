package bo.custom.impl;

import bo.custom.PlaceOrderDetailBo;
import dao.DaoFactory;
import dao.custom.PlaceOrderDetailDao;
import dao.util.DaoType;
import dto.PlaceOrderDetailDto;
import entity.PlaceOrderDetail;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlaceOrderDetailBoImpl implements PlaceOrderDetailBo {
    private PlaceOrderDetailDao placeOrderDetailDao = DaoFactory.getInstance().getDao(DaoType.PLACEORDER);
    @Override
    public boolean saveOrder(PlaceOrderDetailDto dto) throws SQLException, ClassNotFoundException {
        return placeOrderDetailDao.save(new PlaceOrderDetail(
                dto.getOrderId(),
                dto.getEmail(),
                dto.getName(),
                dto.getItemCategory(),
                dto.getItemName(),
                dto.getDate(),
                dto.getFault(),
                dto.getQty(),
                dto.getContactNumber()
        ));
    }

    @Override
    public boolean updateOrder(PlaceOrderDetailDto dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean deleteOrder(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public List<PlaceOrderDetailDto> allPlaceOrders() throws SQLException, ClassNotFoundException {
        List<PlaceOrderDetail> entityList = placeOrderDetailDao.getAll();
        List<PlaceOrderDetailDto> list = new ArrayList<>();
        for (PlaceOrderDetail placeOrderDetail : entityList) {
            list.add(new PlaceOrderDetailDto(
                    placeOrderDetail.getOrderId(),
                    placeOrderDetail.getOrderId(),
                    placeOrderDetail.getContactNumber(),
                    placeOrderDetail.getName(),
                    placeOrderDetail.getItemCategory(),
                    placeOrderDetail.getItemName(),
                    placeOrderDetail.getDate(),
                    placeOrderDetail.getQty(),
                    placeOrderDetail.getFault()
            ));
        }
        return list;
    }

    @Override
    public PlaceOrderDetailDto lastOrderCode() throws SQLException, ClassNotFoundException {
        return placeOrderDetailDao.lastOrderCode();
    }
}
