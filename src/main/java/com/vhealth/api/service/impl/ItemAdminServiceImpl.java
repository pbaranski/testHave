package com.vhealth.api.service.impl;

import com.vhealth.api.dao.ItemDao;
import com.vhealth.api.entity.Item;
import com.vhealth.api.service.ItemAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("itemAdminService")
@Transactional(readOnly = true)
public class ItemAdminServiceImpl implements ItemAdminService {
    @Autowired
    ItemDao itemDao;

    @Override
    public Item findByItemName(String itemName) {
        return itemDao.findItemByName(itemName);
    }

    @Override
    public Item findByItemId(int id) {
        return itemDao.findById(id);
    }

    @Override
    @Transactional(readOnly = false)
    public Item saveItem(Item item) {
        return itemDao.saveItemAndReturn(item);
    }

    @Override
    @Transactional(readOnly = false)
    public void updateItem(Item item) {
        itemDao.updateItem(item);
    }

    @Override
    @Transactional(readOnly = false)
    public void deleteItemById(int id) {
        itemDao.deleteItemById(id);
    }

    @Override
    public List<Item> findItems() {
        return itemDao.findItems();
    }

    @Override
    public List<Item> findItemsByUserName(String userName) {
        return itemDao.findItemsByUserName(userName);
    }
}
