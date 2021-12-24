package com.holovko.springmvc.controller;

import com.holovko.springmvc.dto.EventDTO;
import com.holovko.springmvc.model.Event;
import com.holovko.springmvc.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class EventController {
    @Autowired
    EventService eventService;

    @PostMapping(value = "/events", produces = "application/json")
    public Event createEvent(@RequestBody Event event) {
        return eventService.createEvent(event);
    }

    @GetMapping(value = "/events", produces = "application/json")
    public List<Event> readEvents() {
        return eventService.getEvents();
    }

    @GetMapping(value = "/events/by-start-date", produces = "application/json")
    public List<EventDTO> readEventsByStartDate() {
        return eventService.getEventsByStartDate();
    }

    @GetMapping(value = "/events/{id}", produces = "application/json")
    public Event getEvent(@PathVariable(value = "id") Long id) {
        return eventService.getEvent(id);
    }

    @PutMapping(value="/events/{id}", produces = "application/json")
    public Event readEvent(@PathVariable(value = "id") Long id, @RequestBody Event event) {
        return eventService.updateEvent(id, event);
    }

    @DeleteMapping(value="/events/{id}", produces = "application/json")
    public void deleteEvent(@PathVariable(value = "id") Long id) {
        eventService.deleteEvent(id);
    }

}
