package com.holovko.springmvc.controller;

import com.holovko.springmvc.model.Event;
import com.holovko.springmvc.service.EventService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("events")
@Tag(name = "event", description = "the Event API")

public class EventController {
    @Autowired
    EventService eventService;

    @RequestMapping(value = "/", method= RequestMethod.POST, produces = "application/json")
    public Event createEvent(@RequestBody Event event) {
        return eventService.createEvent(event);
    }

    @GetMapping(value = "/", produces = "application/json")
    public List<Event> readEvents() {
        return eventService.getEvents();
    }

    @RequestMapping(value="/{Id}", method=RequestMethod.PUT, produces = "application/json")
    public Event readEvent(@PathVariable(value = "Id") Long id, @RequestBody Event eventDetails) {
        return eventService.updateEvent(id, eventDetails);
    }

    @RequestMapping(value="/{Id}", method=RequestMethod.DELETE, produces = "application/json")
    public void deleteEvent(@PathVariable(value = "Id") Long id) {
        eventService.deleteEvent(id);
    }
}
