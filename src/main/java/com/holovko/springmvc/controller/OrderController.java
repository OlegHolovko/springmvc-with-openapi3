package com.holovko.springmvc.controller;

import com.holovko.springmvc.dto.order.RequestCreateOrderDTO;
import com.holovko.springmvc.dto.order.RequestUpdateOrderDTO;
import com.holovko.springmvc.exception.EventExpiredException;
import com.holovko.springmvc.exception.EventNotFoundException;
import com.holovko.springmvc.exception.OrderNotFoundException;
import com.holovko.springmvc.exception.TicketsSoldOutException;
import com.holovko.springmvc.model.Order;
import com.holovko.springmvc.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class OrderController {
    @Autowired
    OrderService orderService;

    @PostMapping(value="/orders/events/{eventId}", produces = "application/json")
    public Order createOrder(@PathVariable(value = "eventId") Long eventId,
                             @Valid @RequestBody RequestCreateOrderDTO order)
            throws EventNotFoundException, EventExpiredException, TicketsSoldOutException {
        return orderService.createOrder(eventId, order);
    }

    @GetMapping(value="/orders", produces = "application/json")
    public List<Order> readOrders() {
        return orderService.getOrders();
    }

    @GetMapping(value = "/orders/{id}", produces = "application/json")
    public Order getOrder(@PathVariable(value = "id") Long id) throws OrderNotFoundException {
        return orderService.getOrder(id).orElseThrow(OrderNotFoundException::new);
    }

    @PutMapping(value="/orders/{id}/events/{eventId}", produces = "application/json")
    public Order readOrders(@PathVariable(value = "id") Long id, @PathVariable(value = "eventId") Long eventId,
                            @Valid  @RequestBody RequestUpdateOrderDTO order)
            throws EventNotFoundException, TicketsSoldOutException, EventExpiredException {
        return orderService.updateOrder(id, eventId, order);
    }

    @DeleteMapping(value="/orders/{id}", produces = "application/json")
    public void deleteOrder(@PathVariable(value = "id") Long id) {
        orderService.deleteOrder(id);
    }

}
