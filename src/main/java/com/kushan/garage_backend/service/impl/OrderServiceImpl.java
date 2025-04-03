package com.kushan.garage_backend.service.impl;

import com.kushan.garage_backend.entity.*;
import com.kushan.garage_backend.repository.CartRepository;
import com.kushan.garage_backend.repository.OrderItemRepository;
import com.kushan.garage_backend.repository.OrderRepository;
import com.kushan.garage_backend.repository.UserRepository;
import com.kushan.garage_backend.service.OrderService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public Order checkoutCart(Long userId, String paymentMethod) {
        // Fetch the user
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Fetch the active cart
        Cart cart = cartRepository.findByUserAndStatus(user, "ACTIVE")
                .orElseThrow(() -> new RuntimeException("No active cart found"));

        // Create a new order
        Order order = new Order();
        order.setUser(user);
        order.setStatus("PENDING");
        order.setPaymentMethod(paymentMethod);
        order.setTotalAmount(BigDecimal.ZERO); // Initialize total amount

        // Map CartItems to OrderItems
        BigDecimal totalAmount = BigDecimal.ZERO;
        for (CartItem cartItem : cart.getCartItems()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order); // Set the order reference
            orderItem.setAutoPart(cartItem.getAutoPart());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setUnitPrice(cartItem.getUnitPrice());
            orderItem.setTotalPrice(cartItem.getTotalPrice());

            // Add the OrderItem to the Order
            order.getOrderItems().add(orderItem);

            // Update the total amount
            totalAmount = totalAmount.add(cartItem.getTotalPrice());
        }

        // Set the total amount for the order
        order.setTotalAmount(totalAmount);

        // Save the order (cascades and saves OrderItems)
        orderRepository.save(order);

        // Clear the cart after checkout
        cartRepository.delete(cart);

        return order;
    }

    @Override
    public List<Order> getOrdersByUserId(Long userId) {
        return orderRepository.findByUserId(userId);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll(); // Fetch all orders from the database
    }

    @Override
    public Order getOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found with ID: " + id));
    }

    @Override
    public void updateOrderStatus(Long id, String status) {
        Order order = getOrderById(id); // Fetch the order
        order.setStatus(status); // Update the status
        orderRepository.save(order); // Save the updated order
    }
}
