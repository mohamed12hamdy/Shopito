package com.example.Shopito.Controllers;

import com.example.Shopito.Dtos.cart.CartItemRequestDto;
import com.example.Shopito.Dtos.cart.CartItemResponseDto;
import com.example.Shopito.Entities.users;
import com.example.Shopito.Services.CartService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CartController {

    @Autowired
    private CartService cartService;


    @Operation(
            summary = "view user's cart",
            description = "Get the Authenticated user's cart with products selected."
    )
    @GetMapping("/cart")
    /// @AuthenticationPrincipa get current Authenticated user
    public ResponseEntity<List<CartItemResponseDto>> viewCart(@AuthenticationPrincipal users user) {

        List<CartItemResponseDto> cartItems = cartService.getCartItems(user);
        return ResponseEntity.ok(cartItems);
    }
    @Operation(
            summary = "Add product to user's cart",
            description = "This Endpoint is used to add product to user's cart."
    )
    @PostMapping("/cart")
    public ResponseEntity<?>AddToCart(@Valid @RequestBody CartItemRequestDto requestDto,@AuthenticationPrincipal users user){
        cartService.AddToCart(requestDto,user);
        return ResponseEntity.ok("Item added to cart successfully");
    }






}
