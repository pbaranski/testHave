package com.vhealth.api.controller;

import com.fasterxml.jackson.core.JsonParseException;
import com.vhealth.api.entity.Item;
import com.vhealth.api.service.ItemAdminService;
import com.vhealth.api.utils.ExceptionsResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/api/admin/items")
public class ApiItemAdminController extends ExceptionsResolver {
    @Autowired
    private ItemAdminService itemAdminService;

    @RequestMapping(value = "/{itemId}", produces = "application/json", method = RequestMethod.GET)
    @ResponseBody
    public Item jsonItem(@PathVariable int itemId) {
        return itemAdminService.findByItemId(itemId);
    }

    @RequestMapping(produces = "application/json", method = RequestMethod.GET)
    @ResponseBody
    public List<Item> jsonItems() {
        return itemAdminService.findItems();
    }

    @RequestMapping(consumes = "application/json", produces = "application/json", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity jsonCreateItem(@RequestBody Item item) throws JsonParseException {
        if (itemAdminService.saveItem(item) != null) {
            return new ResponseEntity(item, HttpStatus.ACCEPTED);
        }
        return new ResponseEntity("Item not created", HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/{itemId}", consumes = "application/json", produces = "application/json", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity jsonUpdateItem(@RequestBody Item item, @PathVariable int itemId) throws JsonParseException {
        item.setId(itemId);
        itemAdminService.updateItem(item);
        return new ResponseEntity(item, HttpStatus.ACCEPTED);

    }

    @RequestMapping(value = "/{itemId}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity jsonDeleteItem(@PathVariable int itemId) {
        itemAdminService.deleteItemById(itemId);
        return new ResponseEntity("Item id " + itemId + " deleted", HttpStatus.OK);
    }


}

