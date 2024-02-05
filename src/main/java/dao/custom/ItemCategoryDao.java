package dao.custom;

import dao.CrudDao;
import dto.ItemCategoryDto;
import entity.ItemCategory;
public interface ItemCategoryDao extends CrudDao<ItemCategory>{
    ItemCategoryDto lastItemCode();
}
