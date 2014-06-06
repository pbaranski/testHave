package com.vhealth.api.dao.impl;

import com.vhealth.api.dao.ItemDao;
import com.vhealth.api.entity.Item;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ItemDaoImpl extends AbstractDaoImpl<Item, Integer> implements ItemDao {

    protected ItemDaoImpl() {
        super(Item.class);
    }

    @Override
    public void saveItem(Item item) {
        saveAndReturnObject(item);
    }

    @Override
    public Item saveItemAndReturn(Item item) {
        return saveAndReturnObject(item);
    }

    public void updateItem(Item item) {
        saveOrUpdate(item);
    }

    @Override
    public void deleteItemById(int id) {
        delete(findItemById(id));
    }

    @Override
    public List<Item> findItems() {
        return findByCriteria();
    }

    @Override
    public List<Item> findItemsByUserName(String userName) {
        return findByCriteria("user_username", userName);
    }

    @Override
    public Item findItemById(int id) {
        return entityManager.find(Item.class, id);
    }

    @Override
    public Item findItemByName(String itemName) {
        List<Item> items = findByCriteria("itemName", itemName);
        return items.size() != 0 ? items.get(0) : null;
    }


}
