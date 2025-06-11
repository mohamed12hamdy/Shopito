package com.example.Shopito.Exceptions;

import com.example.Shopito.Entities.cart.Cart;

public class CartNotFound extends RuntimeException{

    public CartNotFound(String message){
        super(message);
    }
}
