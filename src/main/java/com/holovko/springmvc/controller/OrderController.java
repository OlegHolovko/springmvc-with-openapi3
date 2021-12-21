package com.holovko.springmvc.controller;

import com.holovko.springmvc.model.Event;
import com.holovko.springmvc.model.Order;
import com.holovko.springmvc.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    OrderService orderService;

    @PostMapping(value="/", produces = "application/json")
    public Order createOrder(@RequestBody Order order) {
        return orderService.createOrder(order);
    }

    @GetMapping(value="/", produces = "application/json")
    public List<Order> readOrders() {
        return orderService.getOrders();
    }

    @GetMapping(value = "/{Id}", produces = "application/json")
    public Order getOrder(@PathVariable(value = "Id") Long id) {
        return orderService.getOrder(id);
    }

    @PutMapping(value="/{orderId}", produces = "application/json")
    public Order readOrders(@PathVariable(value = "orderId") Long id, @RequestBody Order orderDetails) {
        return orderService.updateOrder(id, orderDetails);
    }

    @DeleteMapping(value="/{orderId}", produces = "application/json")
    public void deleteOrder(@PathVariable(value = "orderId") Long id) {
        orderService.deleteOrder(id);
    }

}
