package com.example.Shopito.Repositories;

import com.example.Shopito.Entities.order.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Orders,Integer> {
}
