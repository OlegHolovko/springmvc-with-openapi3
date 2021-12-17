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

    // CREATE
    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    // READ
    public List<Order> getOrders() {
        return (List<Order>) orderRepository.findAll();
    }

    // DELETE
    public void deleteOrder(Long empId) {
        orderRepository.deleteById(empId);
    }

    // UPDATE
    public Order updateOrder(Long empId, Order orderDetails) {
        Order emp = orderRepository.findById(empId).get();
        emp.setBuyerName(orderDetails.getBuyerName());

        return orderRepository.save(emp);
    }
}
