package com.vhealth.api.controller;

import com.fasterxml.jackson.core.JsonParseException;
import com.vhealth.api.dto.ItemDto;
import com.vhealth.api.service.ItemService;
import org.hibernate.ejb.criteria.expression.function.AggregationFunction;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Pattern;
import java.util.List;

@Controller
@RequestMapping("/api/items/")
@Validated
public class ApiItemController {
    @Autowired
    ItemService itemService;

    @RequestMapping(value = "/{itemId}", produces = "application/json", method = RequestMethod.GET)
    @ResponseBody
    public ItemDto jsonItemDto(@PathVariable int itemId) {
        return itemService.findByItemId(itemId);
    }

    @RequestMapping(value = "/name/{itemName}", produces = "application/json", method = RequestMethod.GET)
    @ResponseBody
    public ItemDto jsonItemDto(@PathVariable String itemName) {
        return itemService.findByItemName(itemName);
    }

    @RequestMapping(produces = "application/json", method = RequestMethod.GET)
    @ResponseBody
    public List<ItemDto> jsonItemsDto() {
        return itemService.findItems();
    }

    @RequestMapping(consumes = "application/json", produces = "application/json", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity jsonCreateItemDto(@Validated @RequestBody ItemDto itemDto) throws JsonParseException {
        itemDto = itemService.saveItem(itemDto);
        return new ResponseEntity(itemDto, HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/{itemId}", consumes = "application/json", produces = "application/json", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity jsonUpdateItemDto(@RequestBody ItemDto itemDto, @PathVariable int itemId) throws JsonParseException {
        itemDto.setId(itemId);
        itemService.updateItem(itemDto);
        return new ResponseEntity(itemDto, HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/{itemId}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity jsonDeleteItem(@PathVariable int itemId) {
        boolean status = itemService.deleteItemById(itemId);
        return new ResponseEntity("Item id " + itemId + " deleted = " + status, HttpStatus.OK);
    }
}
