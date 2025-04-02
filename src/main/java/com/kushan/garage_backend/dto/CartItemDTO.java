package com.kushan.garage_backend.dto;

import com.kushan.garage_backend.entity.CartItem;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class CartItemDTO {
    private Long id;
    private Long autoPartId;
    private String autoPartName;
    private int quantity;
    private BigDecimal unitPrice;
    private BigDecimal totalPrice;
    private String imageUrl;

    public CartItemDTO(CartItem cartItem) {
        this.id = cartItem.getId();
        this.autoPartId = cartItem.getAutoPart().getId();
        this.autoPartName = cartItem.getAutoPart().getName();
        this.quantity = cartItem.getQuantity();
        this.unitPrice = cartItem.getUnitPrice();
        this.totalPrice = cartItem.getTotalPrice();
        this.imageUrl = cartItem.getAutoPart().getImageUrl(); // Map the imageUrl
    }
}
