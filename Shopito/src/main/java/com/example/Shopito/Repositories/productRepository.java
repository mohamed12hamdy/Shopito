package com.example.Shopito.Repositories;

import com.example.Shopito.Entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface productRepository extends JpaRepository<Product,Integer> {

    boolean existsByName(String name);

    List<Product> findByCategoryName(String categoryName);

    List<Product> findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String name, String description);


}
