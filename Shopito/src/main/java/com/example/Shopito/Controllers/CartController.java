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
            summary = "View User's Cart",
            description = """
        Retrieves the authenticated user's shopping cart along with all selected products.

        🛒 Includes product details (name, quantity, price, etc.).
        🔐 Requires valid authentication (JWT or session).
    """
    )

    @GetMapping("/cart")
    /// @AuthenticationPrincipa get current Authenticated user
    public ResponseEntity<List<CartItemResponseDto>> viewCart(@AuthenticationPrincipal users user) {

        List<CartItemResponseDto> cartItems = cartService.getCartItems(user);
        return ResponseEntity.ok(cartItems);
    }
    @Operation(
            summary = "Add Product to User's Cart",
            description = """
        Adds a specified product to the authenticated user's cart.

        🛒 You need to provide the product ID and quantity.
        🔐 Requires authentication.
        ⚠️ If the product already exists in the cart, the quantity may be updated.
    """
    )

    @PostMapping("/cart")
    public ResponseEntity<?>AddToCart( @RequestBody CartItemRequestDto requestDto,@AuthenticationPrincipal users user){
        cartService.AddToCart(requestDto,user);
        return ResponseEntity.ok("Item added to cart successfully");
    }

    @Operation(
            summary = "Update Product Quantity in Cart",
            description = """
        Updates the quantity of a specific product in the authenticated user's cart.

        🛠️ Requires product ID and the new quantity.
        🔐 Authentication is required.
        ⚠️ Quantity must be greater than zero.
    """
    )

    @PutMapping("/cart/update")
    public ResponseEntity<?>UpdateProductQuantity(@RequestBody CartItemRequestDto requestDto,@AuthenticationPrincipal users user){
       boolean updated =       cartService.UpdateProductQuantity(requestDto,user);
       if(updated) return  ResponseEntity.ok("your cart has been updated");

       else
           return  ResponseEntity.ok("An Error has occurred");
    }

    @Operation(
            summary = "Remove Product from User's Cart",
            description = """
        Deletes a specific product from the authenticated user's shopping cart.

        🗑️ Provide the product ID in the path to remove it from the cart.
        🔐 Requires user authentication.
    """
    )


    @DeleteMapping("/cart/remove/{productId}")
    public ResponseEntity<?>DeleteProductFromCart(@PathVariable int productId,@AuthenticationPrincipal users user){
         cartService.DeleteProductFromCart(productId,user);
        return  ResponseEntity.ok("product successfully deleted");
    }



}
