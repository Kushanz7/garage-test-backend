package com.kushan.garage_backend.controller;

import com.kushan.garage_backend.entity.Order;
import com.kushan.garage_backend.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/checkout/{userId}")
    public ResponseEntity<String> checkout(@PathVariable Long userId, @RequestParam String paymentMethod) {
        orderService.checkoutCart(userId, paymentMethod);
        return ResponseEntity.ok("Checkout successful");
    }

}
