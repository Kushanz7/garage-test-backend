package com.kushan.garage_backend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "appointments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "appointment_status", nullable = false)
    private String appointmentStatus;

    @Column(name = "booking_date", nullable = false)
    private LocalDateTime bookingDate;

    @Column(name = "job_status")
    private String jobStatus;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private User customer;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = true)  // Allows null values
    private User employee;

    @Column(name = "price", precision = 38, scale = 2)
    private BigDecimal price;

    @Column(name = "estimate_time")
    private Integer estimateTime; // assuming this is in minutes

    @Column(name = "job_description", length = 1000)
    private String jobDescription;

    @Column(name = "service")
    private String service;

    @Column(name = "service_type")
    private String serviceType;

    @ManyToOne
    @JoinColumn(name = "vehicle_number", nullable = false)  // FK from Vehicle table
    private Vehicle vehicle;


}
