package com.example.Shopito.Dtos.Category;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CategoryResponseDto {

    private int id;

    @NotBlank(message = "Categoryname is required")
    private String name;


    private String description;

}
