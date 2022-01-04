package com.holovko.springmvc.controller;

import com.holovko.springmvc.dto.event.RequestCreateEventDTO;
import com.holovko.springmvc.dto.event.EventsByBuyerDTO;
import com.holovko.springmvc.dto.event.EventCustomDTO;
import com.holovko.springmvc.dto.event.RequestUpdateEventDTO;
import com.holovko.springmvc.exception.EventNotFoundException;
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
    public Event createEvent(@RequestBody RequestCreateEventDTO event) {
        return eventService.createEvent(event);
    }

    @GetMapping(value = "/events/buyer/{buyerName}", produces = "application/json")
    public EventsByBuyerDTO getEventsByBuyer(@PathVariable(value = "buyerName") String buyerName) {
        return eventService.getEventsByBuyer(buyerName);
    }

    @GetMapping(value = "/events", produces = "application/json")
    public List<Event> getEvents() {
        return eventService.getEvents();
    }

    @GetMapping(value = "/events/by-start-date", produces = "application/json")
    public List<EventCustomDTO> getEventsByStartDate() {
        return eventService.getEventsByStartDate();
    }

    @GetMapping(value = "/events/{id}", produces = "application/json")
    public Event getEvent(@PathVariable(value = "id") Long id) throws EventNotFoundException {
        return eventService.getEvent(id).orElseThrow(EventNotFoundException::new);
    }

    @PutMapping(value="/events/{id}", produces = "application/json")
    public Event updateEvent(@PathVariable(value = "id") Long id, @RequestBody RequestUpdateEventDTO event) {
        return eventService.updateEvent(id, event);
    }

    @DeleteMapping(value="/events/{id}", produces = "application/json")
    public void deleteEvent(@PathVariable(value = "id") Long id) {
        eventService.deleteEvent(id);
    }

}
