package com.example.Shopito.Controllers;

import com.example.Shopito.Dtos.cart.CartItemResponseDto;
import com.example.Shopito.Entities.users;
import com.example.Shopito.Services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CartController {

    @Autowired
    private CartService cartService;



    @GetMapping("/cart")
    /// @AuthenticationPrincipa get current Authenticated user
    public ResponseEntity<List<CartItemResponseDto>> viewCart(@AuthenticationPrincipal users user) {

        List<CartItemResponseDto> cartItems = cartService.getCartItems(user);
        return ResponseEntity.ok(cartItems);
    }


}
