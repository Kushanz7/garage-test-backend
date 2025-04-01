package com.kushan.garage_backend.service;

import com.kushan.garage_backend.entity.Appointment;

import java.util.List;

public interface AppointmentService {
    Appointment createAppointment(Appointment appointment);
    Appointment getAppointmentById(Long id);
    List<Appointment> getAllAppointments();
    Appointment updateAppointment(Long id, Appointment appointment);
    void deleteAppointment(Long id);
    List<Appointment> getAppointmentsByCustomerId(Long customerId);
    void finishAppointment(Long id);
}
