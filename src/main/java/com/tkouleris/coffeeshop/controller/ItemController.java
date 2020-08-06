package com.tkouleris.coffeeshop.controller;

import com.tkouleris.coffeeshop.model.Item;
import com.tkouleris.coffeeshop.service.ItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/item")
public class ItemController {

    ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping(path = "/create", produces = "application/json")
    public ResponseEntity<Object> createItem(@RequestBody Item item) throws Exception {
        Item savedItem = itemService.createItem(item);
        return new ResponseEntity<>(savedItem, HttpStatus.CREATED);
    }

    @PutMapping(path = "/update", produces = "application/json")
    public ResponseEntity<Object> updateItem(@RequestBody Item item) throws Exception {
        Item updatedItem = itemService.updateItem(item);
        return new ResponseEntity<>(updatedItem,HttpStatus.OK);
    }

    @DeleteMapping(path ="/delete/{item_id}", produces = "application/json")
    public ResponseEntity<Object> deleteItem(@PathVariable("item_id") long item_id) throws Exception {
        itemService.delete(item_id);
        return new ResponseEntity<>(null,HttpStatus.OK);
    }

}
