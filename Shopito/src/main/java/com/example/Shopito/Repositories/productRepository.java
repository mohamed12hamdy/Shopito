package com.example.Shopito.Repositories;

import com.example.Shopito.Entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface productRepository extends JpaRepository<Product,Integer> {

    boolean existsByName(String name);

    List<Product> findByCategoryName(String categoryName);
}
