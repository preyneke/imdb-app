package com.imdbapp.app.exceptions;

public class TitleNotFoundException extends Exception {

    public TitleNotFoundException(String message){
        super(message);
    }

public TitleNotFoundException(String message, Throwable cause){
        super(message, cause);
}
}
