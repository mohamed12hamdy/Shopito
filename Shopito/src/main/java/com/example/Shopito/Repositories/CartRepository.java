package com.example.Shopito.Repositories;

import com.example.Shopito.Entities.cart.Cart;
import com.example.Shopito.Entities.users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart,Integer> {
    Optional<Cart> findByUser(users user);
}
