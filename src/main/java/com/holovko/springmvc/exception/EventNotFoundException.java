package com.holovko.springmvc.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class EventNotFoundException extends Exception{

    private static final long serialVersionUID = 1L;

    public EventNotFoundException(){
        super("Event not found!");
    }
}
