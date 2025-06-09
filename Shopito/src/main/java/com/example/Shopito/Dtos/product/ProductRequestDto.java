package com.example.Shopito.Dtos.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequestDto {

    private int id;
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

