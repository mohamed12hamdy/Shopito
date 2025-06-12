package com.example.Shopito.Services;

import com.example.Shopito.Dtos.cart.CartItemRequestDto;
import com.example.Shopito.Dtos.cart.CartItemResponseDto;
import com.example.Shopito.Entities.Product;
import com.example.Shopito.Entities.cart.Cart;
import com.example.Shopito.Entities.cart.CartItem;
import com.example.Shopito.Entities.cart.CartItemId;
import com.example.Shopito.Entities.users;
import com.example.Shopito.Exceptions.CartNotFound;
import com.example.Shopito.Exceptions.ProductNotFound;
import com.example.Shopito.Exceptions.ProductquantityNotEnough;
import com.example.Shopito.Repositories.CartItemRepository;
import com.example.Shopito.Repositories.CartRepository;
import com.example.Shopito.Repositories.productRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service

public class CartService {


    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private productRepository repo;




    @Transactional
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
     @Transactional
     public void AddToCart(CartItemRequestDto requestDto, users user){
         Cart userCart = cartRepository.findByUser(user).orElseGet(() -> {
            Cart cart = new Cart();
            cart.setUser(user);
            return cartRepository.save(cart);
        });


        Product product = repo.findById(requestDto.getProductId()).orElseThrow(()-> new ProductNotFound("product not found"));


         if (product.getQuantity() < requestDto.getQuantity()) {
             throw new ProductquantityNotEnough("Not enough stock available");
         }

        CartItemId id = new CartItemId();   //dh kda composite primarykey
        id.setCartId(userCart.getId());
        id.setProductId(product.getId());

        Optional<CartItem> optionalCartItem = cartItemRepository.findById(id);

        if (optionalCartItem.isPresent()) {

            CartItem existingItem = optionalCartItem.get();
            existingItem.setQuantity(existingItem.getQuantity() + requestDto.getQuantity());

            cartItemRepository.save(existingItem);


        }
        else {

            CartItem item = new CartItem();
            item.setId(id);
            item.setCart(userCart);
            item.setProduct(product);
            item.setQuantity(requestDto.getQuantity());

            cartItemRepository.save(item);


        }
        //SharedResource
        product.setQuantity(product.getQuantity()- requestDto.getQuantity());
        repo.save(product);
    }


    @Transactional
    public boolean UpdateProductQuantity(CartItemRequestDto requestDto, users user) {
        Cart userCart = cartRepository.findByUser(user).orElse(null);
        if (userCart == null) return false;

        Product product = repo.findById(requestDto.getProductId()).orElse(null);
        if (product == null) return false;

        CartItemId cartItemId = new CartItemId();
        cartItemId.setCartId(userCart.getId());
        cartItemId.setProductId(product.getId());

        Optional<CartItem> optionalCartItem = cartItemRepository.findById(cartItemId);
        if (optionalCartItem.isPresent()) {
            CartItem cartItem = optionalCartItem.get();

            int oldQuantity = cartItem.getQuantity();
            int newQuantity = requestDto.getQuantity();
            int diff = newQuantity - oldQuantity;

            if (diff > 0) {

                if (product.getQuantity() < diff) {
                    return false;
                }
                product.setQuantity(product.getQuantity() - diff);
            } else if (diff < 0) {

                product.setQuantity(product.getQuantity() + Math.abs(diff));
            }

            cartItem.setQuantity(newQuantity);

            cartItemRepository.save(cartItem);
            repo.save(product);
            return true;
        } else {
            return false;
        }
    }





}
