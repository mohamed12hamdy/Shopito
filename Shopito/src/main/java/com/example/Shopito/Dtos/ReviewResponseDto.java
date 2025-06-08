package com.example.Shopito.Dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewResponseDto {
    private int id;
    private int rating;
    private String comment;
    private String username;

    private int productId;

}
