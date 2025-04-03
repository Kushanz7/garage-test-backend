package com.kushan.garage_backend.controller;

import com.kushan.garage_backend.dto.OrderDTO;
import com.kushan.garage_backend.entity.Order;
import com.kushan.garage_backend.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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

    @GetMapping("/{userId}")
    public ResponseEntity<List<OrderDTO>> getOrdersByUserId(@PathVariable Long userId) {
        List<Order> orders = orderService.getOrdersByUserId(userId);
        List<OrderDTO> orderDTOs = orders.stream()
                .map(OrderDTO::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(orderDTOs);
    }


    @GetMapping("/all")
    public ResponseEntity<List<OrderDTO>> getAllOrders() {
        List<Order> orders = orderService.getAllOrders();
        List<OrderDTO> orderDTOs = orders.stream()
                .map(OrderDTO::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(orderDTOs);
    }

    // Fetch order details by ID
    @GetMapping("byUser/{id}")
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable Long id) {
        Order order = orderService.getOrderById(id);
        return ResponseEntity.ok(new OrderDTO(order));
    }

    // Update order status
    @PutMapping("byUser/{id}")
    public ResponseEntity<String> updateOrderStatus(@PathVariable Long id, @RequestBody OrderDTO orderDTO) {
        orderService.updateOrderStatus(id, orderDTO.getStatus());
        return ResponseEntity.ok("Order status updated successfully!");
    }
}
