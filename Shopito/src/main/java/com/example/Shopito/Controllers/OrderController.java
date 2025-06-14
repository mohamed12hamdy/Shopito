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
            summary = "Place an Order",
            description = """
        This endpoint allows the authenticated user to place an order
        based on the items currently in their cart.

        🛒 The order will include all products added to the user's cart.  
        📦 Inventory will be updated and an order record will be created.  
        🔐 Authentication is required.
    """
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
            description = """
        This endpoint retrieves the details of a specific order 
        made by the authenticated user.

        📦 It includes order items, total price, status, and timestamps.  
        🔐 Authentication is required to access this information.
    """
    )

    @GetMapping("/orders/{id}")
    public ResponseEntity<?>OrderDetails(@PathVariable int id){
        return ResponseEntity.ok(orderService.OrderDetails(id));
    }

    @Operation(
            summary = "Get Current User's Orders",
            description = """
        This endpoint retrieves all orders placed by the currently authenticated user.

        📦 It returns a list of the user's past and current orders.  
        Each order typically includes order ID, total price, status, and timestamps.

        🔐 Requires authentication.
    """
    )

    @GetMapping("/orders")
    public ResponseEntity<?>GetCurrentUserOrders(@AuthenticationPrincipal users user){
        return ResponseEntity.ok(orderService.GetCurrentUserOrders(user));
    }






}
