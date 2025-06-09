package com.example.Shopito.Services;

import com.example.Shopito.Dtos.ReviewRequestDto;
import com.example.Shopito.Dtos.ReviewResponseDto;
import com.example.Shopito.Entities.Product;
import com.example.Shopito.Entities.Review;
import com.example.Shopito.Entities.users;
import com.example.Shopito.Exceptions.ProductNotFound;
import com.example.Shopito.Exceptions.UserNotFoundException;
import com.example.Shopito.Repositories.ReviewRepository;
import com.example.Shopito.Repositories.UserRepository;
import com.example.Shopito.Repositories.productRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewService {
    @Autowired
    private UserRepository userRepository;
    @Autowired

    private ReviewRepository repository;

    @Autowired
    private productRepository productRepository;

    public ReviewResponseDto AddReview(int id, ReviewRequestDto dto, HttpServletRequest request){
        Integer userId = (Integer) request.getAttribute("userId");

        users user = userRepository.findById(userId).orElseThrow(()->new UserNotFoundException("user not found"));
        Product product = productRepository.findById(id).orElseThrow(()->new ProductNotFound("product not found"));
        Review review = new Review();
        review.setRating(dto.getRating());
        review.setComment(dto.getComment());
        review.setUser(user);
        review.setProduct(product);

        Review savedReview = repository.save(review);
        ReviewResponseDto responseDto = new ReviewResponseDto();
        responseDto.setId(review.getId());
        responseDto.setRating(review.getRating());
        responseDto.setComment(review.getComment());
        responseDto.setUsername(review.getUser().getUsername());
        responseDto.setProductId(review.getProduct().getId());
        return responseDto;

    }

    public List<ReviewResponseDto> GetAllReviews(int productId){
        List<Review> reviews = repository.findByProductId(productId);

        return reviews.stream()
                .map(review -> new ReviewResponseDto(
                        review.getId(),
                        review.getRating(),
                        review.getComment(),
                        review.getUser().getUsername(),
                        review.getProduct().getId()
                ))
                .collect(Collectors.toList());
    }





}
