package com.tkouleris.coffeeshop.service;

import com.tkouleris.coffeeshop.exception.item.ItemCreationException;
import com.tkouleris.coffeeshop.exception.item.ItemNotFoundException;
import com.tkouleris.coffeeshop.exception.item.ItemUpdateException;
import com.tkouleris.coffeeshop.model.Item;
import com.tkouleris.coffeeshop.repository.ItemRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {
    ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public List<Item> findAll(Integer pageNo, Integer pageSize, String sortBy) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Page<Item> pagedResult = itemRepository.findAll(paging);
        return pagedResult.getContent();
    }

    public Item createItem(Item item) throws Exception {
        if (itemExists(item.getItem())) {
            throw new ItemCreationException("Item " + item.getItem() + " already exists");
        }
        return itemRepository.save(item);
    }

    public Item updateItem(Item item) throws Exception {
        if (item.getId() == 0) {
            throw new IllegalArgumentException("Item ID not set!");
        }

        Item recordToUpdate = itemRepository.findById(item.getId()).orElse(null);
        if (itemRecordNotExists(recordToUpdate)) {
            throw new ItemNotFoundException("Item does not exist");
        }

        if (itemExists(item.getItem()) && isNotCurrentItem(item, recordToUpdate)) {
            throw new ItemUpdateException("Item " + item.getItem() + " already exists");
        }

        if (item.getItem() != null) recordToUpdate.setItem(item.getItem());
        recordToUpdate.setActive(item.isActive());
        recordToUpdate.setPrice(item.getPrice());

        return itemRepository.save(recordToUpdate);
    }

    public Item getitem(long item_id)
    {
        return itemRepository.findById(item_id).orElse(null);
    }

    public void delete(long item_id) throws Exception {
        Item itemToDelete = itemRepository.findById(item_id).orElse(null);
        if (itemRecordNotExists(itemToDelete)) {
            throw new ItemNotFoundException("Item does not exist!");
        }

        itemRepository.delete(itemToDelete);
    }

    public void setImagesUrl(List<Item> items, String baseUrl) {
        for (Item item : items) {
            setImageUrl(item, baseUrl);
        }
    }

    public void setImageUrl(Item item, String baseUrl) {
        item.image_url = null;
        if (item.getImage_name() != null) {
            item.image_url = item.getImageURL(baseUrl);
        }
    }

    private boolean isNotCurrentItem(Item item, Item recordToUpdate) {
        return !recordToUpdate.getItem().equals(item.getItem());
    }

    private boolean itemRecordNotExists(Item recordToUpdate) {
        return recordToUpdate == null;
    }

    private boolean itemExists(String itemCode) {
        return itemRepository.findFirstByItem(itemCode).orElse(null) != null;
    }


}
