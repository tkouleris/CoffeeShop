package com.tkouleris.coffeeshop.dto.converter;

import com.tkouleris.coffeeshop.dto.requests.OrderRequest;
import com.tkouleris.coffeeshop.model.Item;
import com.tkouleris.coffeeshop.model.Orders;
import com.tkouleris.coffeeshop.model.Tables;
import com.tkouleris.coffeeshop.repository.ItemRepository;
import com.tkouleris.coffeeshop.repository.TableRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class OrderRequestConverter {

    private final TableRepository tableRepository;
    private final ItemRepository itemRepository;

    public OrderRequestConverter(TableRepository tableRepository, ItemRepository itemRepository) {
        this.tableRepository = tableRepository;
        this.itemRepository = itemRepository;
    }

    public Orders convertToEntity(OrderRequest orderRequest) {
        Orders newOrder = new Orders();
        newOrder.setId(orderRequest.getId());
        newOrder.setDate(LocalDate.now());
        newOrder.setDelivered(false);
        newOrder.setPayed(false);

        Tables table = tableRepository.findById(orderRequest.getTable_id()).orElse(null);
        newOrder.setTable(table);

        Item item = itemRepository.findById(orderRequest.getItem_id()).orElse(null);
        newOrder.setItem(item);

        return newOrder;
    }
}
