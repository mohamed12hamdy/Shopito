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
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<?>AddToCart( @RequestBody CartItemRequestDto requestDto,@AuthenticationPrincipal users user){
        cartService.AddToCart(requestDto,user);
        return ResponseEntity.ok("Item added to cart successfully");
    }

    @Operation(
            summary = "Update product",
            description = "This endpoint used to update the quantity needed by a user."
    )
    @PutMapping("/cart/update")
    public ResponseEntity<?>UpdateProductQuantity(@RequestBody CartItemRequestDto requestDto,@AuthenticationPrincipal users user){
       boolean updated =       cartService.UpdateProductQuantity(requestDto,user);
       if(updated) return  ResponseEntity.ok("your cart has been updated");

       else
           return  ResponseEntity.ok("An Error has occurred");
    }



    @DeleteMapping("/cart/remove/{productId}")
    public ResponseEntity<?>DeleteProductFromCart(@PathVariable int productId,@AuthenticationPrincipal users user){
         cartService.DeleteProductFromCart(productId,user);
        return  ResponseEntity.ok("product successfully deleted");
    }



}
