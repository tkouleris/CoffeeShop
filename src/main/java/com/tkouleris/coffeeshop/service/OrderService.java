package com.tkouleris.coffeeshop.service;

import com.tkouleris.coffeeshop.dto.OrderRequest;
import com.tkouleris.coffeeshop.dto.OrdersResponse;
import com.tkouleris.coffeeshop.model.Item;
import com.tkouleris.coffeeshop.model.Orders;
import com.tkouleris.coffeeshop.model.Tables;
import com.tkouleris.coffeeshop.repository.ItemRepository;
import com.tkouleris.coffeeshop.repository.OrdersRepository;
import com.tkouleris.coffeeshop.repository.TableRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
//
//    private final TableRepository tableRepository;
//    private final ItemRepository itemRepository;
    private final OrdersRepository ordersRepository;
//
    public OrderService(TableRepository tableRepository, ItemRepository itemRepository, OrdersRepository ordersRepository)
    {

        this.ordersRepository = ordersRepository;
    }

    public List<Orders> create(List<Orders> orders)
    {
        List<Orders> createdOrders = new ArrayList<>();
        for (Orders order: orders) {
            Orders savedOrder = ordersRepository.save(order);
        }
        return createdOrders;
    }

}
