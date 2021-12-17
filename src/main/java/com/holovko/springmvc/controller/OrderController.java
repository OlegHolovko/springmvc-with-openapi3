package com.holovko.springmvc.controller;

import com.holovko.springmvc.model.Order;
import com.holovko.springmvc.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

public class OrderController {
    @Autowired
    OrderService orderService;

    @RequestMapping(value="/order", method= RequestMethod.POST)
    public Order createOrder(@RequestBody Order order) {
        return orderService.createOrder(order);
    }

    @RequestMapping(value="/orders", method=RequestMethod.GET)
    public List<Order> readOrders() {
        return orderService.getOrders();
    }

    @RequestMapping(value="/orders/{orderId}", method=RequestMethod.PUT)
    public Order readOrders(@PathVariable(value = "orderId") Long id, @RequestBody Order orderDetails) {
        return orderService.updateOrder(id, orderDetails);
    }

    @RequestMapping(value="/order/{orderId}", method=RequestMethod.DELETE)
    public void deleteOrder(@PathVariable(value = "orderId") Long id) {
        orderService.deleteOrder(id);
    }
}
