package com.example.Shopito.Exceptions;

public class PasswordMisMatch extends RuntimeException{

    public PasswordMisMatch(String message){
        super(message);
    }
}
