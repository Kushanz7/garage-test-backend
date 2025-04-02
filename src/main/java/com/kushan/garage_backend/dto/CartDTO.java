package com.kushan.garage_backend.dto;

import com.kushan.garage_backend.entity.Cart;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class CartDTO {
    private Long id;
    private Long userId;
    private String status;
    private LocalDateTime createdAt;
    private List<CartItemDTO> cartItems;

    public CartDTO(Cart cart) {
        this.id = cart.getId();
        this.userId = cart.getUser().getId();
        this.status = cart.getStatus();
        this.createdAt = cart.getCreatedAt();
        this.cartItems = cart.getCartItems().stream()
                .map(CartItemDTO::new)
                .collect(Collectors.toList());
    }
}
