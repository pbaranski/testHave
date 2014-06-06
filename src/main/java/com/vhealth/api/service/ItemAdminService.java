package com.vhealth.api.service;


import com.vhealth.api.entity.Item;

import java.util.List;

public interface ItemAdminService {
    Item findByItemName(String itemName);

    Item findByItemId(int id);

    Item saveItem(Item item);

    void deleteItemById(int id);

    List<Item> findItems();

    List<Item> findItemsByUserName(String userName);

    void updateItem(Item item);

}
