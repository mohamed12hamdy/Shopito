package com.example.Shopito.Repositories;

import com.example.Shopito.Entities.order.OrderItem;
import com.example.Shopito.Entities.order.OrderItemId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemId> {

}
