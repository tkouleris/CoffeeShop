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
import com.tkouleris.coffeeshop.service.OrderService;
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
import java.util.List;

@Controller
@RequestMapping("/api/orders")
public class OrderController {

    private final TableRepository tableRepository;
    private final ItemRepository itemRepository;

    private final OrderService orderService;

    public OrderController(TableRepository tableRepository, ItemRepository itemRepository, OrderService orderService)
    {
        this.tableRepository = tableRepository;
        this.itemRepository = itemRepository;
        this.orderService = orderService;
    }

    @PostMapping( path = "/create", produces = "application/json")
    public ResponseEntity<Object> create(@RequestBody OrdersRequest orders)
    {
        List<Orders> orderList = new ArrayList<>();
        for (OrderRequest order: orders.orders) {
            orderList.add(convertToEntity(order));
        }
        List<Orders> createdOrders = orderService.create(orderList);
        return new ResponseEntity<>(createdOrders, HttpStatus.CREATED);
    }

    private Orders convertToEntity(OrderRequest orderRequest)
    {
        Orders newOrder = new Orders();
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
