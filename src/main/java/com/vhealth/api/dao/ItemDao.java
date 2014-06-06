package com.vhealth.api.dao;

import com.vhealth.api.entity.Item;

import java.util.List;

public interface ItemDao extends AbstractDao<Item, Integer> {
    void saveItem(Item item);

    void deleteItemById(int id);

    List<Item> findItems();

    List<Item> findItemsByUserName(String userName);

    Item findItemById(int Id);

    Item findItemByName(String itemName);

    Item saveItemAndReturn(Item item);

    void updateItem(Item item);
}
