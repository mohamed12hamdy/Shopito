package com.example.Shopito.Dtos.Review;

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
