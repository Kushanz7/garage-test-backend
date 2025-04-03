package com.kushan.garage_backend.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.kushan.garage_backend.entity.OrderItem;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class OrderItemDTO {
    private Long id;
    private String autoPartName;
    private int quantity;
    private BigDecimal price;

    public OrderItemDTO(OrderItem orderItem) {
        this.id = orderItem.getId();
        this.autoPartName = orderItem.getAutoPart().getName();
        this.quantity = orderItem.getQuantity();
        this.price = orderItem.getTotalPrice();
    }

    // Default constructor (required for deserialization)
    public OrderItemDTO() {
    }

    // Constructor for deserialization
    @JsonCreator
    public OrderItemDTO(
            @JsonProperty("id") Long id,
            @JsonProperty("autoPartName") String autoPartName,
            @JsonProperty("quantity") Integer quantity,
            @JsonProperty("price") BigDecimal price
    ) {
        this.id = id;
        this.autoPartName = autoPartName;
        this.quantity = quantity;
        this.price = price;
    }
}
