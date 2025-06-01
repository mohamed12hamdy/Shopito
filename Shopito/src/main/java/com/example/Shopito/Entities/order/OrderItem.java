package com.example.Shopito.Entities.order;

import com.example.Shopito.Entities.Product;
import com.example.Shopito.Entities.cart.CartItemId;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class OrderItem {


    @EmbeddedId
    private OrderItemId id;


    @ManyToOne
    @MapsId("orderId")
   @JoinColumn(name = "order_id",nullable = false)
    private Orders orders;

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

}
