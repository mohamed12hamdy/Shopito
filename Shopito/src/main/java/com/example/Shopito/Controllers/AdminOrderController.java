package com.example.Shopito.Controllers;

import com.example.Shopito.Dtos.Order.OrderItemDto;
import com.example.Shopito.Dtos.Order.OrderResponseDto;
import com.example.Shopito.Entities.order.OrderItem;
import com.example.Shopito.Entities.order.Orders;
import com.example.Shopito.Services.AdminOrderService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/admin/orders")
public class AdminOrderController {

    @Autowired
    private AdminOrderService adminOrderService;

    @Operation(
            summary = "Get All Orders (Admin Only)",
            description = """
        Retrieves a list of all orders in the system including their details.

        🔐 Requires ADMIN role authorization (`@PreAuthorize("hasRole('ADMIN')")`)  
        📦 Returns each order's status, total amount, and list of items (product name, quantity, price).  
        🗂️ Useful for admin dashboard and monitoring order statuses.
    """
    )

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<?>>GetAllOrders(){

        List<Orders>allorders = adminOrderService.GetAllOrders();
        List<OrderResponseDto>AllOrdersDto = new ArrayList<>();
        for(Orders item : allorders){
            OrderResponseDto dto = new OrderResponseDto();
            dto.setStatus(item.getStatus());
            dto.setTotalAmount(item.getTotalAmount());
            List<OrderItemDto> itemDtos = new ArrayList<>();

            for(OrderItem orderItem : item.getItems()){
                OrderItemDto orderItemDto = new OrderItemDto();
                orderItemDto.setProductName(orderItem.getProduct().getName());
                orderItemDto.setQuantity(orderItem.getQuantity());
                orderItemDto.setPrice(orderItem.getPrice());
                itemDtos.add( orderItemDto);

            }
            dto.setItems(itemDtos);
            AllOrdersDto.add(dto);

        }
        return ResponseEntity.ok(AllOrdersDto);

    }





}
