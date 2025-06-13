package com.example.Shopito.Services;

import com.example.Shopito.Dtos.Order.OrderItemDto;
import com.example.Shopito.Dtos.Order.OrderResponseDto;
import com.example.Shopito.Entities.Enums.Status;
import com.example.Shopito.Entities.Product;
import com.example.Shopito.Entities.cart.Cart;
import com.example.Shopito.Entities.cart.CartItem;
import com.example.Shopito.Entities.order.OrderItem;
import com.example.Shopito.Entities.order.OrderItemId;
import com.example.Shopito.Entities.order.Orders;
import com.example.Shopito.Entities.users;
import com.example.Shopito.Repositories.CartRepository;
import com.example.Shopito.Repositories.OrderItemRepository;
import com.example.Shopito.Repositories.OrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private OrderRepository orderRepository;



    @Autowired
    private OrderItemRepository orderItemRepository;


    @Transactional
    public String placeAnOrder(users user){

        Cart cart = cartRepository.findByUser(user).orElse(null);
        if(cart == null || cart.getItems().isEmpty()){
            return "Cart is empty";
        }
        List<CartItem> cartItems = cart.getItems();
        /// kda na m3ia kol cartitems
        double total = 0;
        Orders order = new Orders();
        order.setUser(user);
        order.setStatus(Status.PLACED);
        order.setTotalAmount(0);
        order = orderRepository.save(order);

        List<OrderItem> orderItems = new ArrayList<>();
   /// prodid //orderid
        for(CartItem cartItem : cartItems){
            Product product = cartItem.getProduct();
            int Quantity = cartItem.getQuantity();
            double price = product.getPrice();
            OrderItem orderItem = new OrderItem();
            OrderItemId orderItemId = new OrderItemId();
            orderItemId.setOrderId(order.getId());
            orderItemId.setProductId(product.getId());

            orderItem.setId(orderItemId);
            orderItem.setOrders(order);
            orderItem.setProduct(product);
            orderItem.setPrice(price);
            orderItem.setQuantity(Quantity);
            orderItems.add(orderItem);
            total+=price*Quantity;
        }
        order.setTotalAmount(total);
        order.setItems(orderItems);
        orderRepository.save(order);
        cart.getItems().clear();
        cartRepository.save(cart);
        return "Your order has been received you will be directed to pay. Total: " + total;
    }




    public OrderResponseDto OrderDetails(int id){
        Orders order = orderRepository.findById(id).orElse(null);
        OrderResponseDto dto = new OrderResponseDto();
        if(order != null){
            dto.setTotalAmount(order.getTotalAmount());
            dto.setStatus(order.getStatus());

        }
        List<OrderItemDto> itemDtos = order.getItems().stream()
                .map(item -> {
                    OrderItemDto itemDto = new OrderItemDto();
                    itemDto.setProductName(item.getProduct().getName());
                    itemDto.setQuantity(item.getQuantity());
                    itemDto.setPrice(item.getPrice());
                    return itemDto;
                })
                .collect(Collectors.toList());

        dto.setItems(itemDtos);
        return dto;

    }

}
