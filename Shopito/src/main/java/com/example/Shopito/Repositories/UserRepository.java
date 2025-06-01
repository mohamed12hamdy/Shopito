package com.example.Shopito.Repositories;

import com.example.Shopito.Entities.users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<users,Integer> {
    boolean existsByEmail(String email);
}
