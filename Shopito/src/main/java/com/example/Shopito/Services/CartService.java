package com.example.Shopito.Services;

import com.example.Shopito.Dtos.cart.CartItemResponseDto;
import com.example.Shopito.Entities.cart.Cart;
import com.example.Shopito.Entities.cart.CartItem;
import com.example.Shopito.Entities.users;
import com.example.Shopito.Exceptions.CartNotFound;
import com.example.Shopito.Repositories.CartItemRepository;
import com.example.Shopito.Repositories.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service

public class CartService {


    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;


    public List<CartItemResponseDto>getCartItems(users user)
    {
        Cart cart = cartRepository.findByUser(user)
                .orElseGet(() -> {
                    Cart newCart = new Cart();
                    newCart.setUser(user);
                    return cartRepository.save(newCart);
                });

        //kda a7na 3mlna empty cart lw mknsh fe ll user dh
        List<CartItem> items = cartItemRepository.findByCart(cart);

        return items.stream()
                .map(item -> new CartItemResponseDto(

                        item.getProduct().getName(),
                        item.getProduct().getPrice(),
                        item.getQuantity()
                )).collect(Collectors.toList());
    }
}
