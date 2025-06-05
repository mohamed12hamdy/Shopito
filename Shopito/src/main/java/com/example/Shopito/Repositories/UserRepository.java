package com.example.Shopito.Repositories;

import com.example.Shopito.Entities.users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<users,Integer> {
    boolean existsByEmail(String email);
    Optional<users> findByUsername(String username);


}
