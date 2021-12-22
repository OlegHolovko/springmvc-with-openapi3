package com.holovko.springmvc.service;

import com.holovko.springmvc.dto.OrderDTO;
import com.holovko.springmvc.model.Event;
import com.holovko.springmvc.model.Order;
import com.holovko.springmvc.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;

    public Order createOrder(OrderDTO orderDTO) {
        Order order = orderDTO.getOrder();
        //order.setBuyerName(orderDTO.getBuyerName());
        //order.setAmount(orderDTO.getAmount());
        //order.setTotalPrice(orderDTO.getTotalPrice());
        //order.setEvent(orderDTO.getOrder().getEvent());
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

    public Order updateOrder(Long orderId, OrderDTO orderDTO) {
        Order order = orderRepository.findById(orderId).get();
        order.setBuyerName(orderDTO.getBuyerName());
        order.setAmount(orderDTO.getAmount());
        order.setTotalPrice(orderDTO.getTotalPrice());
        order.setEvent(orderDTO.getOrder().getEvent());
        return orderRepository.save(order);
    }
}
