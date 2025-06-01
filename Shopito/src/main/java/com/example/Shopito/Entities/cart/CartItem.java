package com.example.Shopito.Entities.cart;

import com.example.Shopito.Entities.Product;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data

public class CartItem {
    ///  a7na 3mlna class gded asmo CartItemId w 5lena feh el 2 fields elle hykon part of the composite key
    /// then here ast5dmna @EmbeddedId w b3deen 3mlna @MapId
    @EmbeddedId
    private CartItemId id;

    @ManyToOne
    @MapsId("cartId")    ///this is part of the primary key
    @JoinColumn(name = "cart_id",nullable = false)
    private Cart cart;

    @ManyToOne
    @MapsId("productId")   ///this is part of the primary key also
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(nullable = false)
    private int quantity;

}
