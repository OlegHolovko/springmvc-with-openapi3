package com.holovko.springmvc.exception;

public class TiсketsSoldOutException extends Exception{
    private static final long serialVersionUID = 1L;

    public TiсketsSoldOutException(){
        super("All tickets sold out!");
    }
}
