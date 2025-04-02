package com.kushan.garage_backend.service;

import com.kushan.garage_backend.entity.Order;

public interface OrderService {
    public Order checkoutCart(Long userId, String paymentMethod);

}
