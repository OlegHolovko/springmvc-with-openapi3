package com.holovko.springmvc.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class TicketsSoldOutException extends Exception{
    private static final long serialVersionUID = 2L;

    public TicketsSoldOutException(){
        super("Have not enough tickets!");
    }
}
