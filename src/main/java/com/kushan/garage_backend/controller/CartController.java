package com.kushan.garage_backend.controller;

import com.kushan.garage_backend.dto.CartDTO;
import com.kushan.garage_backend.dto.CartItemRequest;
import com.kushan.garage_backend.entity.Cart;
import com.kushan.garage_backend.entity.CartItem;
import com.kushan.garage_backend.service.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<CartDTO> getCart(@PathVariable Long userId) {
        Cart cart = cartService.getActiveCart(userId);
        return ResponseEntity.ok(new CartDTO(cart));
    }

    @PostMapping("/{userId}/add")
    public ResponseEntity<String> addItem(@PathVariable Long userId, @RequestBody CartItemRequest request) {
        cartService.addItemToCart(userId, request.getPartId(), request.getQuantity());
        return ResponseEntity.ok("Item added successfully");
    }

    @PutMapping("/{userId}/update")
    public ResponseEntity<String> updateItemQuantity(@PathVariable Long userId, @RequestBody CartItemRequest request) {
        cartService.updateItemQuantity(userId, request.getPartId(), request.getQuantity());
        return ResponseEntity.ok("Item quantity updated successfully");
    }

    @DeleteMapping("/remove/{itemId}")
    public void removeItem(@PathVariable Long itemId) {
        cartService.removeItemFromCart(itemId);
    }
}
