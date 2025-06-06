package com.example.Shopito.Exceptions;

public class ProductAlreadyExist extends  RuntimeException{
    public ProductAlreadyExist(String message){
        super(message);
    }
}
