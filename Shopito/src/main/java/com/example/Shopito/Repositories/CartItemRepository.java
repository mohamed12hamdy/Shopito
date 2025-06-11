package com.example.Shopito.Repositories;

import com.example.Shopito.Entities.cart.Cart;
import com.example.Shopito.Entities.cart.CartItem;
import com.example.Shopito.Entities.cart.CartItemId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, CartItemId> {

    List<CartItem> findByCart(Cart cart);

}
