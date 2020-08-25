package com.tkouleris.coffeeshop.controller;

import com.tkouleris.coffeeshop.dto.OrderRequest;
import com.tkouleris.coffeeshop.dto.OrdersRequest;
import com.tkouleris.coffeeshop.dto.OrdersResponse;
import com.tkouleris.coffeeshop.model.Item;
import com.tkouleris.coffeeshop.model.Orders;
import com.tkouleris.coffeeshop.model.Tables;
import com.tkouleris.coffeeshop.repository.ItemRepository;
import com.tkouleris.coffeeshop.repository.OrdersRepository;
import com.tkouleris.coffeeshop.repository.TableRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

@Controller
@RequestMapping("/api/orders")
public class OrderController {

    private final OrdersRepository ordersRepository;
    private final TableRepository tableRepository;
    private final ItemRepository itemRepository;

    public OrderController(OrdersRepository ordersRepository, TableRepository tableRepository, ItemRepository itemRepository)
    {
        this.ordersRepository = ordersRepository;
        this.tableRepository = tableRepository;
        this.itemRepository = itemRepository;
    }

    @PostMapping( path = "/create", produces = "application/json")
    public ResponseEntity<Object> create(@RequestBody OrdersRequest orders)
    {
        OrdersResponse ordersResponse = new OrdersResponse();
        for (OrderRequest order: orders.orders) {
            Orders newOrder = new Orders();
            newOrder.setDate(LocalDate.now());
            newOrder.setDelivered(false);
            newOrder.setPayed(false);

            Tables table = tableRepository.findById(order.getTable_id()).orElse(null);
            newOrder.setTable(table);

            Item item = itemRepository.findById(order.getItem_id()).orElse(null);
            newOrder.setItem(item);

            Orders savedOrder = ordersRepository.save(newOrder);
            ordersResponse.orders.add(savedOrder);
        }

        return new ResponseEntity<>(ordersResponse, HttpStatus.CREATED);
    }
}
