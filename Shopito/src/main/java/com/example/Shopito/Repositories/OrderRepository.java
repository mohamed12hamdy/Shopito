package com.example.Shopito.Repositories;

import com.example.Shopito.Entities.Enums.Status;
import com.example.Shopito.Entities.order.Orders;
import com.example.Shopito.Entities.users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Orders,Integer> {

    List<Orders> findByUserId(int id);


    @Query("SELECT SUM(o.totalAmount) FROM Orders o WHERE o.user = :user AND o.status = 'PENDING'")
    Double findPendingTotalAmountForUser(@Param("user") users user);

    List<Orders> findByUserIdAndStatus(int userId, Status status);


}
