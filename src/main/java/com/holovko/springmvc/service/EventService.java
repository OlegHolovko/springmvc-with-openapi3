package com.holovko.springmvc.service;

import com.holovko.springmvc.model.Event;
import com.holovko.springmvc.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

    public Event getEvent(Long eventId) {
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
}
