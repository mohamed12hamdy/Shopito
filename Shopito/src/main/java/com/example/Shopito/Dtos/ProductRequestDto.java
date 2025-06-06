package com.example.Shopito.Dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class ProductRequestDto {

    @NotBlank(message = "ProductName is required, please enter name for it")
    private String name;

    private String description;

    @Positive(message = "Price must be positive")
    private double price;

    @Positive(message = "Quantity must be positive")
    private int quantity;

    private String image;

    private int categoryId;


}

