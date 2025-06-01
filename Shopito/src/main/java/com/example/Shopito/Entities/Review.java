package com.example.Shopito.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Review {
    @Id
    private int id;


    @Column(nullable = false)
    private int rating;

    private String comment;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private users user;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;


}
