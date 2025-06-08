package com.example.Shopito.Dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ReviewRequestDto {



    private int rating;
    private String comment;




    private int productId;

}
