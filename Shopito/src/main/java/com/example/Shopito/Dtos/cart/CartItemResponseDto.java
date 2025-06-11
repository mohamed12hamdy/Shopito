package com.example.Shopito.Dtos.cart;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItemResponseDto {



    private String productName;
    private double price;
    private int quantity;




}
