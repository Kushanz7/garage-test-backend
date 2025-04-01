package com.kushan.garage_backend.service.impl;

import com.kushan.garage_backend.entity.Appointment;
import com.kushan.garage_backend.repository.AppointmentRepository;
import com.kushan.garage_backend.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;

    @Autowired
    public AppointmentServiceImpl(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    @Override
    public Appointment createAppointment(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    @Override
    public Appointment getAppointmentById(Long id) {
        return appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));
    }

    @Override
    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    @Override
    public Appointment updateAppointment(Long id, Appointment appointment) {
        Appointment existingAppointment = getAppointmentById(id);
        existingAppointment.setAppointmentStatus(appointment.getAppointmentStatus());
        existingAppointment.setBookingDate(appointment.getBookingDate());
        existingAppointment.setJobStatus(appointment.getJobStatus());
        existingAppointment.setPrice(appointment.getPrice());
        existingAppointment.setEstimateTime(appointment.getEstimateTime());
        existingAppointment.setJobDescription(appointment.getJobDescription());
        existingAppointment.setService(appointment.getService());
        existingAppointment.setServiceType(appointment.getServiceType());
        existingAppointment.setVehicle(appointment.getVehicle());
        existingAppointment.setEmployee(appointment.getEmployee());
        return appointmentRepository.save(existingAppointment);
    }

    @Override
    public void deleteAppointment(Long id) {
        appointmentRepository.deleteById(id);
    }

    public List<Appointment> getAppointmentsByCustomerId(Long customerId) {
        return appointmentRepository.findByCustomerId(customerId);
    }

    public void finishAppointment(Long id) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));
        appointment.setAppointmentStatus("finished");
        appointmentRepository.save(appointment);
    }

}
