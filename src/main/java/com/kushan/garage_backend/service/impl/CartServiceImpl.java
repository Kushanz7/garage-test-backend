package com.kushan.garage_backend.service.impl;

import com.kushan.garage_backend.entity.AutoPart;
import com.kushan.garage_backend.entity.Cart;
import com.kushan.garage_backend.entity.CartItem;
import com.kushan.garage_backend.entity.User;
import com.kushan.garage_backend.repository.AutoPartRepository;
import com.kushan.garage_backend.repository.CartItemRepository;
import com.kushan.garage_backend.repository.CartRepository;
import com.kushan.garage_backend.repository.UserRepository;
import com.kushan.garage_backend.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private AutoPartRepository autoPartRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Cart getActiveCart(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        return cartRepository.findByUserAndStatus(user, "ACTIVE")
                .orElseGet(() -> {
                    Cart newCart = new Cart();
                    newCart.setUser(user);
                    return cartRepository.save(newCart);
                });
    }

    @Override
    public void updateItemQuantity(Long userId, Long partId, int quantity) {
        Cart cart = getActiveCart(userId); // Fetch the active cart for the user
        System.out.println("User ID: " + userId);
        System.out.println("Part ID: " + partId);

        CartItem cartItem = cart.getCartItems().stream()
                .filter(item -> item.getAutoPart().getId().equals(partId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Item not found in cart"));

        System.out.println("Cart Item Found: " + cartItem);

        cartItem.setQuantity(quantity);
        cartItem.setTotalPrice(cartItem.getUnitPrice().multiply(BigDecimal.valueOf(quantity))); // Use multiply method
        cartItemRepository.save(cartItem); // Save the updated cart item
    }

    @Override
    public CartItem addItemToCart(Long userId, Long partId, int quantity) {
        Cart cart = getActiveCart(userId);
        AutoPart part = autoPartRepository.findById(partId).orElseThrow(() -> new RuntimeException("Part not found"));

        CartItem item = new CartItem();
        item.setCart(cart);
        item.setAutoPart(part);
        item.setQuantity(quantity);
        item.setUnitPrice(part.getPrice());
        item.setTotalPrice(part.getPrice().multiply(BigDecimal.valueOf(quantity)));

        return cartItemRepository.save(item);
    }

    @Override
    public void removeItemFromCart(Long itemId) {
        cartItemRepository.deleteById(itemId);
    }
}
