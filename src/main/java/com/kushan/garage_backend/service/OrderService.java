package com.kushan.garage_backend.service;

import com.kushan.garage_backend.entity.Order;

import java.util.List;

public interface OrderService {
    public Order checkoutCart(Long userId, String paymentMethod);
    List<Order> getOrdersByUserId(Long userId);
    List<Order> getAllOrders();
    Order getOrderById(Long id);
    void updateOrderStatus(Long id, String status);
}
