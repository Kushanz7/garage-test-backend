package com.kushan.garage_backend.repository;

import com.kushan.garage_backend.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findByCustomerId(Long customerId);
    // Count appointments by job status (fix naming issue)
    long countByAppointmentStatus(String appointmentStatus);

    // Get total revenue for completed appointments
    @Query("SELECT COALESCE(SUM(a.actualPrice), 0) FROM Appointment a WHERE a.jobStatus = 'completed'")
    Double getTotalRevenue();
}
