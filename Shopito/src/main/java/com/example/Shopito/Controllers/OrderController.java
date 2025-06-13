package com.example.Shopito.Controllers;

import com.example.Shopito.Entities.order.Orders;
import com.example.Shopito.Entities.users;
import com.example.Shopito.Services.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class OrderController {

    @Autowired
   private OrderService orderService;

    @Operation(
            summary = "Place your order",
            description = "This Endpoint is used to place user's order."
    )
    @PostMapping("/orders")
    public ResponseEntity<?> placeOrder(@AuthenticationPrincipal users user) {
        String response = orderService.placeAnOrder(user);

        Map<String, String> message = new HashMap<>();
        message.put("message", response);

        if (response.equals("Cart is empty")) {
            return ResponseEntity.badRequest().body(message);
        }

        return ResponseEntity.ok(message);
    }


    @Operation(
            summary = "Get Order Details",
            description = "This Endpoint is used to get order's Details."
    )
    @GetMapping("/orders/{id}")
    public ResponseEntity<?>OrderDetails(@PathVariable int id){
        return ResponseEntity.ok(orderService.OrderDetails(id));
    }
    @GetMapping("/orders")
    public ResponseEntity<?>GetCurrentUserOrders(@AuthenticationPrincipal users user){
        return ResponseEntity.ok(orderService.GetCurrentUserOrders(user));
    }






}
