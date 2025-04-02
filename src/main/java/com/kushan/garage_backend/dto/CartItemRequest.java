package com.kushan.garage_backend.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CartItemRequest {
    // Getters and Setters
    private Long partId;
    private int quantity;

}

