package com.holovko.springmvc.controller;

import com.holovko.springmvc.model.Order;
import com.holovko.springmvc.service.OrderService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "order", description = "the Order API")
public class OrderController {
    @Autowired
    OrderService orderService;
/*
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

 */
}
