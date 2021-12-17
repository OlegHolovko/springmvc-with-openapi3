package com.holovko.springmvc.controller;

import com.holovko.springmvc.model.Event;
import com.holovko.springmvc.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

public class EventController {
    @Autowired
    EventService eventService;

    @RequestMapping(value="/events", method= RequestMethod.POST)
    public Event createEvent(@RequestBody Event event) {
        return eventService.createEvent(event);
    }

    @RequestMapping(value="/events", method=RequestMethod.GET)
    public List<Event> readEvents() {
        return eventService.getEvents();
    }

    @RequestMapping(value="/events/{eventId}", method=RequestMethod.PUT)
    public Event readEvent(@PathVariable(value = "eventId") Long id, @RequestBody Event eventDetails) {
        return eventService.updateEvent(id, eventDetails);
    }

    @RequestMapping(value="/events/{eventId}", method=RequestMethod.DELETE)
    public void deleteEvent(@PathVariable(value = "eventId") Long id) {
        eventService.deleteEvent(id);
    }
}
