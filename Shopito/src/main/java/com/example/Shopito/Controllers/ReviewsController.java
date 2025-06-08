package com.example.Shopito.Controllers;

import com.example.Shopito.Dtos.ReviewRequestDto;
import com.example.Shopito.Dtos.ReviewResponseDto;
import com.example.Shopito.Services.ReviewService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReviewsController {

    @Autowired
   private ReviewService service;

    @PostMapping("/products/{id}/review")
    public ResponseEntity<?>AddReview(@PathVariable int id, @Valid @RequestBody ReviewRequestDto dto, HttpServletRequest request){
        ReviewResponseDto answer = service.AddReview(id,dto,request);
        return ResponseEntity.ok(answer);

    }







}
