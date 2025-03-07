package com.kushan.garage_backend.controller;

import com.kushan.garage_backend.entity.Appointment;
import com.kushan.garage_backend.repository.AppointmentRepository;
import com.kushan.garage_backend.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

    private final AppointmentService appointmentService;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @PostMapping
    public Appointment createAppointment(@RequestBody Appointment appointment) {
        return appointmentService.createAppointment(appointment);
    }

    @GetMapping("/{id}")
    public Appointment getAppointmentById(@PathVariable Long id) {
        return appointmentService.getAppointmentById(id);
    }

    @GetMapping
    public List<Appointment> getAllAppointments() {
        return appointmentService.getAllAppointments();
    }

    @PutMapping("/{id}")
    public Appointment updateAppointment(@PathVariable Long id, @RequestBody Appointment appointment) {
        return appointmentService.updateAppointment(id, appointment);
    }

    @DeleteMapping("/{id}")
    public void deleteAppointment(@PathVariable Long id) {
        appointmentService.deleteAppointment(id);
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<Appointment>> getAppointmentsByCustomerId(@PathVariable Long customerId) {
        List<Appointment> appointments = appointmentService.getAppointmentsByCustomerId(customerId);
        return ResponseEntity.ok(appointments);
    }

    @GetMapping("/employee-jobs/{employeeId}")
    public ResponseEntity<Map<String, Long>> getEmployeeJobCounts(@PathVariable Long employeeId) {
        Map<String, Long> jobCounts = new HashMap<>();

        long totalJobs = appointmentRepository.countByEmployeeId(employeeId);
        long pendingJobs = appointmentRepository.countByEmployeeIdAndJobStatus(employeeId, "pending");
        long startedJobs = appointmentRepository.countByEmployeeIdAndJobStatus(employeeId, "started");
        long finishedJobs = appointmentRepository.countByEmployeeIdAndJobStatus(employeeId, "finished");

        jobCounts.put("totalJobs", totalJobs);
        jobCounts.put("pendingJobs", pendingJobs);
        jobCounts.put("startedJobs", startedJobs);
        jobCounts.put("finishedJobs", finishedJobs);

        return ResponseEntity.ok(jobCounts);
    }

    @GetMapping("/employee/{employeeId}")
    public List<Appointment> getAppointmentsByEmployee(@PathVariable Long employeeId) {
        return appointmentRepository.findByEmployeeId(employeeId);
    }

    // AppointmentController.java

    @PutMapping("/{id}/jobStatus")
    public ResponseEntity<?> updateJobStatus(@PathVariable Long id, @RequestBody Map<String, String> requestBody) {
        try {
            Optional<Appointment> appointmentOptional = appointmentRepository.findById(id);
            if (appointmentOptional.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Appointment not found");
            }

            Appointment appointment = appointmentOptional.get();
            String newJobStatus = requestBody.get("jobStatus");

            appointment.setJobStatus(newJobStatus);
            appointmentRepository.save(appointment);

            return ResponseEntity.ok("Job status updated successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating job status");
        }
    }


}
