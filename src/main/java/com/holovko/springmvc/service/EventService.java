package com.holovko.springmvc.service;

import com.holovko.springmvc.dto.BuyerCustomDTO;
import com.holovko.springmvc.dto.EventCustomDTO;
import com.holovko.springmvc.dto.EventForBuyerDTO;
import com.holovko.springmvc.model.Event;
import com.holovko.springmvc.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public List<EventCustomDTO> getEventsByStartDate() {
        ArrayList<Event> list = (ArrayList<Event>) eventRepository.findAllByOrderByStartDateAsc();
        ArrayList<EventCustomDTO> listDTO = new ArrayList<>();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        int i = 0;
        for( Event event : list){
            EventCustomDTO eventCustomDTO = new EventCustomDTO(++i, event.getName(), event.getStartDate().format(dateTimeFormatter) );
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

    public Event updateEvent(Long eventId, Event eventDetails) {
        Event event = eventRepository.findById(eventId).orElseThrow();
        event.setName(eventDetails.getName());
        event.setAmount(eventDetails.getAmount());
        event.setPrice(eventDetails.getPrice());
        event.setStartDate(eventDetails.getStartDate());
        return eventRepository.save(event);
    }

    public BuyerCustomDTO getEventsByBuyer(String buyerName) {
        ArrayList<String> list = (ArrayList<String>) eventRepository.findEventsByBuyer(buyerName);
        ArrayList<EventForBuyerDTO> listEvents = new ArrayList<>();
        int i = 0;
        for( String eventName : list){
            EventForBuyerDTO eventForBuyerDTO = new EventForBuyerDTO(++i, eventName);
            listEvents.add(eventForBuyerDTO);
        }
        return new BuyerCustomDTO(buyerName, listEvents);
    }
}
