package com.tkouleris.coffeeshop.service;

import com.tkouleris.coffeeshop.model.Orders;
import com.tkouleris.coffeeshop.repository.OrdersRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    private final OrdersRepository ordersRepository;

    public OrderService(OrdersRepository ordersRepository) {
        this.ordersRepository = ordersRepository;
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
}
