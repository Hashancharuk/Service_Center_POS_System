package bo.custom.impl;

import bo.custom.ItemCategoryBo;
import dao.DaoFactory;
import dao.custom.ItemCategoryDao;
import dao.util.DaoType;
import dto.ItemCategoryDto;
import entity.ItemCategory;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemCategoryBoImpl implements ItemCategoryBo {
    private ItemCategoryDao itemCategoryDao = DaoFactory.getInstance().getDao(DaoType.ITEMCATEGORY);

    @Override
    public boolean saveItem(ItemCategoryDto dto) throws SQLException, ClassNotFoundException {
        try {
            ItemCategory itemCategory = new ItemCategory(dto.getId(), dto.getName(), dto.getCategory());
            return itemCategoryDao.save(itemCategory);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public boolean updateItem(ItemCategoryDto dto) throws SQLException, ClassNotFoundException {
        return itemCategoryDao.update(new ItemCategory(
                dto.getId(),
                dto.getName(),
                dto.getCategory()
        ));
    }

    @Override
    public boolean deleteItem(String id) throws SQLException, ClassNotFoundException {
        return itemCategoryDao.delete(id);
    }

    @Override
    public List<ItemCategoryDto> allItems() throws SQLException, ClassNotFoundException {
        List<ItemCategory> entityList = itemCategoryDao.getAll();
        List<ItemCategoryDto> list = new ArrayList<>();

        if (entityList != null) {
            for (ItemCategory itemCategory : entityList) {
                list.add(new ItemCategoryDto(
                        itemCategory.getId(),
                        itemCategory.getCategory(),
                        itemCategory.getName()
                ));
            }
        }

        return list;
    }

    @Override
    public ItemCategoryDto lastItemCode() throws SQLException, ClassNotFoundException {
        return itemCategoryDao.lastItemCode();
    }

}
