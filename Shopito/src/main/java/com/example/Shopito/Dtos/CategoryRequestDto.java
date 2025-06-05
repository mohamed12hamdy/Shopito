package com.example.Shopito.Dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CategoryRequestDto {



    @NotBlank(message = "Categoryname is required")
    private String name;


    private String description;



}
