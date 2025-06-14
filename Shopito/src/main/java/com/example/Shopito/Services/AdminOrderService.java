package com.example.Shopito.Services;

import com.example.Shopito.Entities.order.Orders;
import com.example.Shopito.Repositories.OrderRepository;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminOrderService {

    @Autowired
    private OrderRepository orderRepository;
    public List<Orders>GetAllOrders(){
        return orderRepository.findAll();
    }

}
