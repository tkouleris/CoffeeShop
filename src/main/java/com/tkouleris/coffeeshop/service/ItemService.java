package com.tkouleris.coffeeshop.service;

import com.tkouleris.coffeeshop.model.Item;
import com.tkouleris.coffeeshop.model.Tables;
import com.tkouleris.coffeeshop.repository.ItemRepository;
import org.springframework.stereotype.Service;

@Service
public class ItemService {
    ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public Item createItem(Item item) throws Exception {
        if (itemExists(item.getItem())) {
            throw new Exception("Item " + item.getItem() + " already exists");
        }
        return itemRepository.save(item);
    }

    public Item updateItem(Item item) throws Exception {
        if (item.getId() == 0) {
            throw new Exception("Item ID not set!");
        }

        Item recordToUpdate = itemRepository.findById(item.getId()).orElse(null);
        if (itemRecordNotExists(recordToUpdate)) {
            throw new Exception("Item does not exist");
        }

        if (itemExists(item.getItem())) {
            throw new Exception("Item " + item.getItem() + " already exists");
        }

        if(item.getItem() != null) recordToUpdate.setItem(item.getItem());
        recordToUpdate.setActive(item.isActive());
        recordToUpdate.setPrice(item.getPrice());

        return itemRepository.save(recordToUpdate);
    }

    private boolean itemRecordNotExists(Item recordToUpdate) {
        return recordToUpdate == null;
    }

    private boolean itemExists(String itemCode) {
        return  itemRepository.findFirstByItem(itemCode).orElse(null) != null;
    }

}
