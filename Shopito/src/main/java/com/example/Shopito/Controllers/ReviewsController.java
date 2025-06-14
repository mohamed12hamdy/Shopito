package com.example.Shopito.Controllers;

import com.example.Shopito.Dtos.Review.ReviewRequestDto;
import com.example.Shopito.Dtos.Review.ReviewResponseDto;
import com.example.Shopito.Services.ReviewService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ReviewsController {

    @Autowired
   private ReviewService service;

    @Operation(
            summary = "Add Review to a Product",
            description = """
        This endpoint allows an **authenticated user** to submit a review for a specific product.

        ✍️ The review typically includes a rating (e.g. 1–5 stars) and an optional comment.  
        ⭐ Helps other users make informed decisions by viewing shared feedback.

        The product must exist, and the user must be logged in to submit a review.
    """
    )

    @PostMapping("/products/{id}/review")
    public ResponseEntity<?>AddReview(@PathVariable int id, @Valid @RequestBody ReviewRequestDto dto, HttpServletRequest request){
        ReviewResponseDto answer = service.AddReview(id,dto,request);
        return ResponseEntity.ok(answer);

    }
    @Operation(
            summary = "Get All Reviews for a Product",
            description = """
        This endpoint retrieves all customer reviews for a specific product.

        ⭐ Each review includes details such as rating, comment, and the user who submitted it.  
        📦 Useful for displaying feedback and ratings on the product details page.

        You must provide the product ID to fetch its associated reviews.
    """
    )

    @GetMapping("/products/{productId}/reviews")
    public ResponseEntity<List<ReviewResponseDto>> getProductReviews(@PathVariable int productId) {
        List<ReviewResponseDto> reviews = service.GetAllReviews(productId);
        return ResponseEntity.ok(reviews);
    }










}
