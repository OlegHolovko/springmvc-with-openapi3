package com.holovko.springmvc.service;

import com.holovko.springmvc.dto.BuyerDTO;
import com.holovko.springmvc.dto.EventDTO;
import com.holovko.springmvc.dto.EventForBuyerDTO;
import com.holovko.springmvc.model.Event;
import com.holovko.springmvc.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class EventService {
    @Autowired
    EventRepository eventRepository;

    public Event createEvent(Event event) {
        return eventRepository.save(event);
    }

    public List<Event> getEvents() {
        return (List<Event>) eventRepository.findAll();
    }

    public List<EventDTO> getEventsByStartDate() {
        ArrayList<Event> list = (ArrayList<Event>) eventRepository.findAllByOrderByStartDateAsc();
        ArrayList<EventDTO> listDTO = new ArrayList<>();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        int i = 0;
        for( Event event : list){
            EventDTO eventDTO = new EventDTO(++i, event.getName(), event.getStartDate().format(dateTimeFormatter) );
            listDTO.add(eventDTO);
        }
        return listDTO;
    }

    public Event getEvent(Long eventId) {
        if (eventRepository.findById(eventId).isEmpty()) {
            throw new NotFoundException("Event not found!");
        }
        return eventRepository.findById(eventId).get();
    }

    public void deleteEvent(Long eventId) {
        eventRepository.deleteById(eventId);
    }

    public Event updateEvent(Long eventId, Event eventDetails) {
        Event event = eventRepository.findById(eventId).get();
        event.setName(eventDetails.getName());
        event.setAmount(eventDetails.getAmount());
        event.setPrice(eventDetails.getPrice());
        event.setStartDate(eventDetails.getStartDate());
        return eventRepository.save(event);
    }

    public BuyerDTO getEventsByBuyer(String buyerName) {
        ArrayList<String> list = (ArrayList<String>) eventRepository.findEventsByBuyer(buyerName);
        ArrayList<EventForBuyerDTO> listEvents = new ArrayList<>();
        int i = 0;
        for( String eventName : list){
            EventForBuyerDTO eventForBuyerDTO = new EventForBuyerDTO(++i, eventName);
            listEvents.add(eventForBuyerDTO);
        }
        BuyerDTO buyerDTO = new BuyerDTO(buyerName, listEvents);
        return buyerDTO;
    }
}
