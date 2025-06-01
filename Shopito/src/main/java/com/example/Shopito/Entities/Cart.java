package com.example.Shopito.Entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @OneToOne
    @JoinColumn(name = "user_id",nullable = false)

    private users user;
}
