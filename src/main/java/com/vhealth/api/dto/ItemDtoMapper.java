package com.vhealth.api.dto;

import com.vhealth.api.entity.Item;
import com.vhealth.api.entity.User;

import java.util.ArrayList;
import java.util.List;

public class ItemDtoMapper {

    public static Item generateItemFromItemDto(ItemDto itemDto, User user) {
        if (itemDto == null) return null;
        Item item = new Item();
        item.setItemName(itemDto.getItemName());
        item.setAmount(itemDto.getAmount());
        item.setDescription(itemDto.getDescription());
        item.setStartTime(itemDto.getStartTime());
        item.setEndTime(itemDto.getEndTime());
        item.setPrice(itemDto.getPrice());
        item.setUser(user);

        return item;
    }

    public static ItemDto generateItemDtoFromItem(Item item) {
        if (item == null) return null;
        ItemDto itemDto = new ItemDto();
        itemDto.setItemName(item.getItemName());
        itemDto.setAmount(item.getAmount());
        itemDto.setDescription(item.getDescription());
        itemDto.setStartTime(item.getStartTime());
        itemDto.setEndTime(item.getEndTime());
        itemDto.setPrice(item.getPrice());
        itemDto.setId(item.getId());

        return itemDto;
    }

    public static List<ItemDto> generateItemDtoListFromItemList(List<Item> items) {
        List<ItemDto> itemsDto = new ArrayList<ItemDto>();
        for (Item item : items) {
            itemsDto.add(generateItemDtoFromItem(item));
        }
        return itemsDto;
    }

}
