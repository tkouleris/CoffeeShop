package com.tkouleris.coffeeshop.controller;

import com.tkouleris.coffeeshop.dto.ApiResponse;
import com.tkouleris.coffeeshop.dto.converter.OrderRequestConverter;
import com.tkouleris.coffeeshop.dto.requests.OrderRequest;
import com.tkouleris.coffeeshop.dto.requests.OrdersRequest;
import com.tkouleris.coffeeshop.model.Orders;
import com.tkouleris.coffeeshop.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderRequestConverter orderRequestConverter;
    private final OrderService orderService;

    public OrderController(OrderRequestConverter orderRequestConverter, OrderService orderService) {
        this.orderService = orderService;
        this.orderRequestConverter = orderRequestConverter;
    }

    @GetMapping(path = "/table_unpaid/{table_id}", produces = "application/json")
    public ResponseEntity<Object> getTableUnpaid(@PathVariable("table_id") long table_id){
        List<Orders> unpaidTableOrders = orderService.getUnpaidTableOrders(table_id);
        ApiResponse apiResponse = new ApiResponse(true,"unpaid table orders",unpaidTableOrders);
        return new ResponseEntity<>(apiResponse.getBodyResponse(),HttpStatus.OK);
    }


    @PostMapping(path = "/create", produces = "application/json")
    public ResponseEntity<Object> create(@RequestBody OrdersRequest orders) {
        List<Orders> orderList = new ArrayList<>();
        for (OrderRequest order : orders.orders) {
            orderList.add(orderRequestConverter.convertToEntity(order));
        }
        List<Orders> createdOrders = orderService.create(orderList);
        return new ResponseEntity<>(createdOrders, HttpStatus.CREATED);
    }

    @PutMapping(path = "/update", produces = "application/json")
    public ResponseEntity<Object> update(@RequestBody OrdersRequest orders) {
        List<Orders> orderList = new ArrayList<>();
        for (OrderRequest order : orders.orders) {
            orderList.add(orderRequestConverter.convertToEntity(order));
        }
        List<Orders> updateOrders = orderService.update(orderList);
        return new ResponseEntity<>(updateOrders, HttpStatus.OK);
    }

    @DeleteMapping(path = "/delete/{order_id}", produces = "application/json")
    public ResponseEntity<Object> delete(@PathVariable("order_id") long order_id) throws Exception {
        orderService.delete(order_id);
        return new ResponseEntity<>(null,HttpStatus.OK);
    }
}
