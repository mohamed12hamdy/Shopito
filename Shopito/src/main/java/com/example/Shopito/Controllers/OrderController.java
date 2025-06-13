package com.example.Shopito.Controllers;

import com.example.Shopito.Entities.users;
import com.example.Shopito.Services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    @Autowired
   private OrderService orderService;

    @PostMapping("/orders")
    public ResponseEntity<?>placeOrder(@AuthenticationPrincipal users user){
        String response = orderService.placeAnOrder(user);
        if (response.equals("Cart is empty")) {
            return ResponseEntity.badRequest().body(response);
        }
        return ResponseEntity.ok(response);

    }
}
