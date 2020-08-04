package com.tkouleris.coffeeshop.service;

import com.tkouleris.coffeeshop.model.Item;
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

    private boolean itemExists(String itemCode) {
        return itemRepository.findFirstByItem(itemCode).orElse(null) != null;
    }
}
