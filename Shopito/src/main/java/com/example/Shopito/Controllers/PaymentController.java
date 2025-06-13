package com.example.Shopito.Controllers;

import com.example.Shopito.Entities.users;
import com.example.Shopito.Repositories.OrderRepository;
import com.stripe.Stripe;
import com.stripe.model.Charge;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    private String secretKey = "sk_test_51RZdDMQln5gThqPjGQ0LPoN2ijW0N2mAhTcyc6racLDS5MNHo4kfysNOUnCxp6z2DHLyaCoXWQeppG2OR5dJmdgk0001Aw5JsA";


    @Autowired
    private OrderRepository orderRepository;

    private Double GetPaymentForCurrentUser(users user){
           Double total = orderRepository.findTotalAmountForUser(user);
           return total;
    }

    @Operation(
            summary = "Checkout Endpoint",
            description = "This endpoint used to simulate Payment process through stripe API"
    )
    @Transactional
    @PostMapping("/checkout")
    public ResponseEntity<?> checkout(
            @AuthenticationPrincipal users user,
            @RequestParam String token,
            @RequestParam int amount) {

        try {

            Stripe.apiKey = secretKey;

            Map<String, Object> params = new HashMap<>();
            params.put("amount", amount);
            Double total = GetPaymentForCurrentUser(user);
            params.put("currency", "usd");


            params.put("source", token);
            params.put("description", "Test Payment");


            Charge charge = Charge.create(params);

            if (total > amount) {
                return ResponseEntity
                        .badRequest()
                        .body(Map.of("error", "Insufficient balance"));
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
