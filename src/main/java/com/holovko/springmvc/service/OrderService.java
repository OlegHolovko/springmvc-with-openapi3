package com.holovko.springmvc.service;

import com.holovko.springmvc.exception.EventExpiredException;
import com.holovko.springmvc.exception.EventNotFoundException;
import com.holovko.springmvc.exception.TiсketsSoldOutException;
import com.holovko.springmvc.model.Event;
import com.holovko.springmvc.model.Order;
import com.holovko.springmvc.repository.EventRepository;
import com.holovko.springmvc.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    EventRepository eventRepository;

    public Order createOrder(Long eventId, Order order)
            throws EventNotFoundException, EventExpiredException, TiсketsSoldOutException {
        if(!orderRepository.existsById(eventId))
            throw new EventNotFoundException();
        Event event = getEvent(eventId);
        if(event.getAmount() - orderRepository.getAmountSumByEventId(eventId) - order.getAmount() < 0)
            throw new TiсketsSoldOutException();
        order.setEvent(event);
        order.setTotalPrice(event.getPrice()*order.getAmount());
        return orderRepository.save(order);
    }

    public List<Order> getOrders() {
        return (List<Order>) orderRepository.findAll();
    }

    public Optional<Order> getOrder(Long orderId) {
        return orderRepository.findById(orderId);
    }

    public void deleteOrder(Long orderId) {
        orderRepository.deleteById(orderId);
    }

    public Order updateOrder(Long orderId, Long eventId, Order orderDetails)
            throws EventNotFoundException, EventExpiredException, TiсketsSoldOutException {
        Order order = orderRepository.findById(orderId).orElseThrow();
        order.setBuyerName(orderDetails.getBuyerName());
        order.setAmount(orderDetails.getAmount());
        Event event = getEvent(eventId);
        if(event.getAmount() - orderRepository.getAmountSumByEventId(eventId) - order.getAmount() < 0)
            throw new TiсketsSoldOutException();
        order.setEvent(event);
        order.setTotalPrice(event.getPrice()*orderDetails.getAmount());
        return orderRepository.save(order);
    }

    private Event getEvent(Long eventId) throws EventNotFoundException, EventExpiredException {
        Event event = eventRepository.findById(eventId).orElseThrow(EventNotFoundException::new);
        if( LocalDateTime.now().isBefore(event.getStartDate()))
            throw new EventExpiredException();
        return event;
    }

}
