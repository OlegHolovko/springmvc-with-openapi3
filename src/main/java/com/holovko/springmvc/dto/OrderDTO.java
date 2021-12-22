package com.holovko.springmvc.dto;

import com.holovko.springmvc.model.Event;
import com.holovko.springmvc.model.Order;
import com.holovko.springmvc.service.EventService;

public class OrderDTO {
    private Long Id;
    private String buyerName;
    private Integer amount;
    private Integer totalPrice;
    private Long eventId;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
    }


    public Order getOrder() {
        Order order = new Order();
        if(this.getId()!=null){
            order.setId(this.getId());
        }
        order.setBuyerName(this.getBuyerName());
        order.setAmount(this.getAmount());
        order.setTotalPrice(this.getTotalPrice());
        if(this.getEventId()!= null) {
            EventService eventService = new EventService();
            try {
                Event event = eventService.getEvent(this.getEventId());
                order.setEvent(event);
            } catch (Exception ignored) {
                System.out.println(ignored.getMessage());
            }
        }
        return order;
    }
}
