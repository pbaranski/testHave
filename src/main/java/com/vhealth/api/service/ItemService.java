package com.vhealth.api.service;


import com.vhealth.api.dto.ItemDto;

import java.util.List;

public interface ItemService {
    ItemDto findByItemName(String itemName);

    ItemDto findByItemId(int id);

    ItemDto saveItem(ItemDto itemDto);

    boolean deleteItemById(int id);

    List<ItemDto> findItems();

    void updateItem(ItemDto itemDto);

}
