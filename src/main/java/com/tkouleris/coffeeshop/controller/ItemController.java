package com.tkouleris.coffeeshop.controller;

import com.tkouleris.coffeeshop.dto.ApiResponse;
import com.tkouleris.coffeeshop.model.Item;
import com.tkouleris.coffeeshop.service.ItemService;
import com.tkouleris.coffeeshop.utils.HttpUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/api/item")
public class ItemController {

    ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping(path = "/all", produces = "application/json")
    public ResponseEntity<Object> all(@RequestParam(defaultValue = "0") Integer pageNo,
                                      @RequestParam(defaultValue = "5") Integer pageSize,
                                      @RequestParam(defaultValue = "id") String sortBy,
                                      HttpServletRequest request) {
        List<Item> items = itemService.findAll(pageNo, pageSize, sortBy);
        String baseUrl = HttpUtils.getBaseUrl(request);
        itemService.setImagesUrl(items, baseUrl);
        ApiResponse apiResponse = new ApiResponse(true,"items list",items);
        return new ResponseEntity<>(apiResponse.getBodyResponse(), HttpStatus.OK);
    }

    @GetMapping(path = "{item_id}", produces = "application/json")
    public ResponseEntity<Object> get_item(@PathVariable("item_id") long item_id){
        Item item = itemService.getitem(item_id);
        ApiResponse apiResponse = new ApiResponse(true,"item",item);
        return new ResponseEntity<>(apiResponse.getBodyResponse(),HttpStatus.OK);
    }

    @PostMapping(path = "/create", produces = "application/json")
    public ResponseEntity<Object> createItem(@RequestBody Item item) throws Exception {
        Item savedItem = itemService.createItem(item);
        ApiResponse apiResponse = new ApiResponse(true,"item saved",savedItem);
        return new ResponseEntity<>(apiResponse.getBodyResponse(), HttpStatus.CREATED);
    }

    @PutMapping(path = "/update", produces = "application/json")
    public ResponseEntity<Object> updateItem(@RequestBody Item item) throws Exception {
        Item updatedItem = itemService.updateItem(item);
        ApiResponse apiResponse = new ApiResponse(true,"item updated",updatedItem);
        return new ResponseEntity<>(apiResponse.getBodyResponse(), HttpStatus.OK);
    }

    @DeleteMapping(path = "/delete/{item_id}", produces = "application/json")
    public ResponseEntity<Object> deleteItem(@PathVariable("item_id") long item_id) throws Exception {
        itemService.delete(item_id);
        ApiResponse apiResponse = new ApiResponse(true,"item deleted",null);
        return new ResponseEntity<>(apiResponse.getBodyResponse(), HttpStatus.OK);
    }

}
