package com.example.Shopito.Repositories;

import com.example.Shopito.Entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review,Integer> {

    List<Review> findByProductId(int productId);

}
