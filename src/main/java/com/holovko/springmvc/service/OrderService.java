package com.holovko.springmvc.service;

import com.holovko.springmvc.model.Order;
import com.holovko.springmvc.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;

    public Order createOrder(Order order) {
        if(order.getEventId()!= null) {
            order.setEvent(orderRepository.findEventByIdNativeQuery(order.getEventId()));
        }
        return orderRepository.save(order);
    }

    public List<Order> getOrders() {
        return (List<Order>) orderRepository.findAll();
    }

    public Order getOrder(Long orderId) {
        return orderRepository.findById(orderId).get();
    }

    public void deleteOrder(Long orderId) {
        orderRepository.deleteById(orderId);
    }

    public Order updateOrder(Long orderId, Order orderDetails) {
        Order order = orderRepository.findById(orderId).get();
        order.setBuyerName(orderDetails.getBuyerName());
        order.setAmount(orderDetails.getAmount());
        order.setTotalPrice(orderDetails.getTotalPrice());
        if(orderDetails.getEventId()!= null) {
            order.setEvent(orderRepository.findEventByIdNativeQuery(orderDetails.getEventId()));
        }
        return orderRepository.save(order);
    }
}
