package com.vhealth.api.service.impl;

import com.vhealth.api.dao.ItemDao;
import com.vhealth.api.dao.UserDao;
import com.vhealth.api.dto.ItemDto;
import com.vhealth.api.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.vhealth.api.dto.ItemDtoMapper.*;

@Service("itemService")
@Transactional(readOnly = true)
public class ItemServiceImpl implements ItemService {
    @Autowired
    ItemDao itemDao;
    @Autowired
    UserDao userDao;

    @Override
    public ItemDto findByItemName(String itemName) {
        return generateItemDtoFromItem(itemDao.findItemByName(itemName));
    }

    @Override
    public ItemDto findByItemId(int id) {
        return generateItemDtoFromItem(itemDao.findById(id));
    }

    @Override
    @Transactional(readOnly = false)
    public ItemDto saveItem(ItemDto itemDto) {
        //TODO changing DTO to Entity and then Entity to DTO - maybe I missing something?
        return generateItemDtoFromItem(
                itemDao.saveItemAndReturn(
                        generateItemFromItemDto(itemDto, userDao.getLoggedUser())));
    }

    @Override
    @Transactional(readOnly = false)
    public void updateItem(ItemDto itemDto) {
        //TODO should I extract userDao.getLoggedUser() to UserDto variable since it is used few times?
        itemDao.updateItem(generateItemFromItemDto(itemDto, userDao.getLoggedUser()));
    }

    @Override
    @Transactional(readOnly = false)
    public boolean deleteItemById(int id) {
        if (userDao.getLoggedUser().getUserName().equals(itemDao.findItemById(id).getUserName())) {
            itemDao.deleteItemById(id);
            return true;
        }
        return false;
    }

    @Override
    public List<ItemDto> findItems() {
        //TODO is there a sens in adding static import for one static method usage
        return generateItemDtoListFromItemList(itemDao.findItemsByUserName(userDao.getLoggedUser().getUserName()));
    }

}
