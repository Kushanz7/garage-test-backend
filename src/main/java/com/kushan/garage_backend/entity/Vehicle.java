package com.kushan.garage_backend.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "vehicle")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Vehicle {

    @Id
    @Column(name = "vehicle_number")
    private String vehicleNumber;

    private String color;
    private double currentRange;
    private String description;
    private String fuelType;
    private String model;
    private int year;

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private User customer;   // Reference to User entity
}
