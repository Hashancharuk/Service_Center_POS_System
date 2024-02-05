package bo.custom;

import bo.SuperBo;
import dto.ItemCategoryDto;

import java.sql.SQLException;
import java.util.List;

public interface ItemCategoryBo extends SuperBo {
    boolean saveItem(ItemCategoryDto dto)throws SQLException, ClassNotFoundException;
    boolean updateItem(ItemCategoryDto dto)throws SQLException, ClassNotFoundException;
    boolean deleteItem(String id) throws SQLException, ClassNotFoundException;
    List<ItemCategoryDto> allItems() throws SQLException, ClassNotFoundException;

    ItemCategoryDto lastItemCode() throws SQLException, ClassNotFoundException;
}
