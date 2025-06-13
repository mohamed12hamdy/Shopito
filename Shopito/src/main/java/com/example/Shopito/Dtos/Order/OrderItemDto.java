package com.example.Shopito.Dtos.Order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class OrderItemDto {
    private String productName;
    private int quantity;
    private double price;

}
