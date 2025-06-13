package com.example.Shopito.Controllers;

import com.example.Shopito.Entities.users;
import com.example.Shopito.Services.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    @Autowired
   private OrderService orderService;

    @Operation(
            summary = "Place your order",
            description = "This Endpoint is used to place user's order."
    )
    @PostMapping("/orders")
    public ResponseEntity<?>placeOrder(@AuthenticationPrincipal users user){
        String response = orderService.placeAnOrder(user);
        if (response.equals("Cart is empty")) {
            return ResponseEntity.badRequest().body(response);
        }
        return ResponseEntity.ok(response);

    }


    @GetMapping("/orders/{id}")
    public ResponseEntity<?>OrderDetails(@PathVariable int id){

    }


}
