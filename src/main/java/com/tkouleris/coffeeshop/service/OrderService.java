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

}
