package com.example.Shopito.Entities.order;

import com.example.Shopito.Entities.Enums.Status;
import com.example.Shopito.Entities.cart.CartItem;
import com.example.Shopito.Entities.users;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Orders{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    private users user;

    @Column(nullable = false)
    private double totalAmount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    @OneToMany(mappedBy = "orders", cascade = CascadeType.ALL, orphanRemoval = true)
    //if we delete OrderItem from Order it will be also deleted from the DB SIDE
    private List<OrderItem> items = new ArrayList<>();
}
