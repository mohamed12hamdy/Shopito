package com.example.Shopito.Exceptions;

public class ProductNotFound extends RuntimeException{
    public ProductNotFound(String message) {
        super(message);
    }

}
