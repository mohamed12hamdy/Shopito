package com.example.Shopito.Dtos.Order;

import com.example.Shopito.Entities.Enums.Status;
import com.example.Shopito.Entities.order.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponseDto {

    private double totalAmount;

    private Status status;

    private List<OrderItem> items = new ArrayList<>();

}
