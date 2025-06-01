package com.example.Shopito.Entities.order;

import com.example.Shopito.Entities.cart.CartItemId;

import java.io.Serializable;
import java.util.Objects;

public class OrderItemId implements Serializable {
    private int orderId;
    private int productId;

    public OrderItemId() {
    }

    public OrderItemId(int orderId, int productId) {
        this.orderId = orderId;
        this.productId = productId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderItemId)) return false;
        OrderItemId that = (OrderItemId) o;
        return Objects.equals(orderId, that.orderId) &&
                Objects.equals(productId, that.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, productId);
    }

}
