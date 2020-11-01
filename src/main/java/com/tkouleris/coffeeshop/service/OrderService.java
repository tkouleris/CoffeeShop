package com.tkouleris.coffeeshop.service;

import com.tkouleris.coffeeshop.dto.responses.OrderDefaultsResponse;
import com.tkouleris.coffeeshop.model.Item;
import com.tkouleris.coffeeshop.model.Orders;
import com.tkouleris.coffeeshop.repository.ItemRepository;
import com.tkouleris.coffeeshop.repository.OrdersRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private final OrdersRepository ordersRepository;
    private final ItemRepository itemRepository;

    public OrderService(OrdersRepository ordersRepository, ItemRepository itemRepository){
        this.ordersRepository = ordersRepository;
        this.itemRepository = itemRepository;
    }

    public List<Orders> create(List<Orders> orders) {
        List<Orders> createdOrders = new ArrayList<>();
        for (Orders order : orders) {
            Orders savedOrder = ordersRepository.save(order);
            createdOrders.add(savedOrder);
        }
        return createdOrders;
    }

    public List<Orders> update(List<Orders> orders) {
        List<Orders> updatedOrders = new ArrayList<>();
        for (Orders order : orders) {
            Orders entityToUpdate = ordersRepository.findById(order.getId()).orElse(null);
            if(entityToUpdate == null) continue;
            entityToUpdate.setItem(order.getItem());
            entityToUpdate.setTable(order.getTable());
            entityToUpdate.setPayed(order.isPayed());
            entityToUpdate.setDelivered(order.isDelivered());
            Orders updatedOrder = ordersRepository.save(entityToUpdate);
            updatedOrders.add(updatedOrder);
        }
        return updatedOrders;
    }

    public void delete(long order_id) throws Exception {
        Orders orderToDelete = ordersRepository.findById(order_id).orElse(null);
        if(orderToDelete == null)
        {
            throw new Exception("Order does not exist");
        }
        ordersRepository.delete(orderToDelete);
    }

    public List<Orders> getUnpaidTableOrders(long table_id) {
        return ordersRepository.findUnpaidByTableId(table_id);
    }

    public OrderDefaultsResponse loadDefault() {
        List<Item> items = (List<Item>) this.itemRepository.findAll();
        OrderDefaultsResponse orderDefaultsResponse = new OrderDefaultsResponse();
        orderDefaultsResponse.items = items;

        return orderDefaultsResponse;
    }
}
