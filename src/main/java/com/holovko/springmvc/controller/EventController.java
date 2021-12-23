package com.holovko.springmvc.controller;

import com.holovko.springmvc.model.Event;
import com.holovko.springmvc.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/events")
public class EventController {
    @Autowired
    EventService eventService;

    @PostMapping(value = "/", produces = "application/json")
    public Event createEvent(@RequestBody Event event) {
        return eventService.createEvent(event);
    }

    @GetMapping(value = "/", produces = "application/json")
    public List<Event> readEvents() {
        return eventService.getEvents();
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public Event getEvent(@PathVariable(value = "id") Long id) {
        return eventService.getEvent(id);
    }

    @PutMapping(value="/{id}", produces = "application/json")
    public Event readEvent(@PathVariable(value = "id") Long id, @RequestBody Event eventDetails) {
        return eventService.updateEvent(id, eventDetails);
    }

    @DeleteMapping(value="/{id}", produces = "application/json")
    public void deleteEvent(@PathVariable(value = "id") Long id) {
        eventService.deleteEvent(id);
    }

}
