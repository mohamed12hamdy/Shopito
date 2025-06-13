package com.example.Shopito.Controllers;

import com.example.Shopito.Entities.Enums.Status;
import com.example.Shopito.Entities.order.Orders;
import com.example.Shopito.Entities.users;
import com.example.Shopito.Repositories.OrderRepository;
import com.stripe.Stripe;
import com.stripe.model.Charge;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/payment")
public class PaymentController {

   
    private String secretKey;

    @Autowired
    private OrderRepository orderRepository;

    private Double getPendingTotalForUser(users user) {
        Double total = orderRepository.findPendingTotalAmountForUser(user);
        System.out.println("Total in dollars: " + total);
        return total != null ? total : 0.0;
    }

    @Transactional
    @PostMapping("/checkout")
    public ResponseEntity<?> checkout(
            @AuthenticationPrincipal users user,
            @RequestParam String token,
            @RequestParam int amount) { // amount in cents

        try {
            Stripe.apiKey = secretKey;

            // Step 1: get total in cents
            Double total = getPendingTotalForUser(user);
            int requiredAmountInCents = (int) Math.round(total * 100);

            System.out.println("User paying: " + amount + " cents");
            System.out.println("Total required: " + requiredAmountInCents + " cents");

            if (requiredAmountInCents > amount) {
                return ResponseEntity.badRequest().body(Map.of("error", "Insufficient balance"));
            }

            // Step 2: charge
            Map<String, Object> params = new HashMap<>();
            params.put("amount", amount); // in cents
            params.put("currency", "usd");
            params.put("source", token);
            params.put("description", "Test Payment");

            Charge charge = Charge.create(params);

            // Step 3: mark orders as PAID
            List<Orders> pendingOrders = orderRepository.findByUserIdAndStatus(user.getId(), Status.PENDING);
            for (Orders o : pendingOrders) {
                o.setStatus(Status.PAID);
                orderRepository.save(o);
            }

            return ResponseEntity.ok(Map.of(
                    "status", charge.getStatus(),
                    "chargeId", charge.getId()
            ));

        } catch (Exception e) {
            return ResponseEntity
                    .badRequest()
                    .body(Map.of("error", e.getMessage()));
        }
    }
}
