package com.example.Shopito.Exceptions;

import org.springframework.web.bind.annotation.ControllerAdvice;


public class UserAlreadyExists extends RuntimeException{
    public UserAlreadyExists(String message) {
        super(message);
    }
}
