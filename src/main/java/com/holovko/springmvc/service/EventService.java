package com.holovko.springmvc.service;

import com.holovko.springmvc.dto.event.*;
import com.holovko.springmvc.model.Event;
import com.holovko.springmvc.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EventService {
    @Autowired
    EventRepository eventRepository;

    final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public Event createEvent(RequestCreateEventDTO eventDetails) {
        Event event = new Event();
        event.setName(eventDetails.getName());
        event.setAmount(eventDetails.getAmount());
        event.setPrice(eventDetails.getPrice());
        event.setStartDate(LocalDateTime.parse(eventDetails.getStartDate(), dateTimeFormatter));
        return eventRepository.save(event);
    }

    public List<Event> getEvents() {
        return (List<Event>) eventRepository.findAll();
    }

    public List<EventCustomDTO> getEventsByStartDate() {
        List<Event> list = eventRepository.findAllByOrderByStartDateAsc();
        List<EventCustomDTO> listDTO = new ArrayList<>();
        DateTimeFormatter dateTimeFormatterShort = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        int i = 0;
        for( Event event : list){
            EventCustomDTO eventCustomDTO = new EventCustomDTO(++i, event.getName(), event.getStartDate().format(dateTimeFormatterShort) );
            listDTO.add(eventCustomDTO);
        }
        return listDTO;
    }

    public Optional<Event> getEvent(Long eventId) {
        return eventRepository.findById(eventId);
    }

    public void deleteEvent(Long eventId) {
        eventRepository.deleteById(eventId);
    }

    public Event updateEvent(Long eventId, RequestUpdateEventDTO eventDetails) {
        Event event = eventRepository.findById(eventId).orElseThrow();
        event.setName(eventDetails.getName());
        event.setAmount(eventDetails.getAmount());
        event.setPrice(eventDetails.getPrice());
        event.setStartDate(LocalDateTime.parse(eventDetails.getStartDate(), dateTimeFormatter));
        return eventRepository.save(event);
    }

    public EventsByBuyerDTO getEventsByBuyer(String buyerName) {
        List<String> list = eventRepository.findEventsByBuyer(buyerName);
        List<EventForBuyerDTO> listEvents = new ArrayList<>();
        int i = 0;
        for( String eventName : list){
            EventForBuyerDTO eventForBuyerDTO = new EventForBuyerDTO(++i, eventName);
            listEvents.add(eventForBuyerDTO);
        }
        return new EventsByBuyerDTO(buyerName, listEvents);
    }
}
