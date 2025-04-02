package com.kushan.garage_backend.service;

import com.kushan.garage_backend.entity.Cart;
import com.kushan.garage_backend.entity.CartItem;

public interface CartService {
    public void removeItemFromCart(Long itemId);
    public CartItem addItemToCart(Long userId, Long partId, int quantity);
    public Cart getActiveCart(Long userId);
    void updateItemQuantity(Long userId, Long partId, int quantity);
}
