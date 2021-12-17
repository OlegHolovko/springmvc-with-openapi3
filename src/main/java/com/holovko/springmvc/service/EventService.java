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

    // CREATE
    public Event createEvent(Event event) {
        return eventRepository.save(event);
    }

    // READ
    public List<Event> getEvents() {
        return (List<Event>) eventRepository.findAll();
    }

    // DELETE
    public void deleteEvent(Long eventId) {
        eventRepository.deleteById(eventId);
    }

    // UPDATE
    public Event updateEvent(Long eventId, Event eventDetails) {
        Event event = eventRepository.findById(eventId).get();
        event.setName(eventDetails.getName());

        return eventRepository.save(event);
    }
}
