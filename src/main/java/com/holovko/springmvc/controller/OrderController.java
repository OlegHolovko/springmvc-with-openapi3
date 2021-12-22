package com.holovko.springmvc.controller;

import com.holovko.springmvc.dto.OrderDTO;
import com.holovko.springmvc.model.Order;
import com.holovko.springmvc.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    OrderService orderService;

    @PostMapping(value="/", produces = "application/json")
    public Order createOrder(@RequestBody OrderDTO orderDTO) {
        System.out.println("CreateOrderRoute");
        return orderService.createOrder(orderDTO);
    }

    @GetMapping(value="/", produces = "application/json")
    public List<Order> readOrders() {
        return orderService.getOrders();
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public Order getOrder(@PathVariable(value = "id") Long id) {
        return orderService.getOrder(id);
    }

    @PutMapping(value="/{id}", produces = "application/json")
    public Order readOrders(@PathVariable(value = "id") Long id, @RequestBody OrderDTO orderDTO) {
        return orderService.updateOrder(id, orderDTO);
    }

    @DeleteMapping(value="/{id}", produces = "application/json")
    public void deleteOrder(@PathVariable(value = "id") Long id) {
        orderService.deleteOrder(id);
    }

}
