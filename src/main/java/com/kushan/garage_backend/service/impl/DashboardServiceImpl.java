package com.kushan.garage_backend.service.impl;

import com.kushan.garage_backend.repository.AppointmentRepository;
import com.kushan.garage_backend.repository.AutoPartRepository;
import com.kushan.garage_backend.repository.UserRepository;
import com.kushan.garage_backend.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service
public class DashboardServiceImpl implements DashboardService {

    @Autowired
    private AppointmentRepository appointmentRepo;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private AutoPartRepository autoPartRepo;

    @Override
    public Map<String, Object> getDashboardStats() {
        Map<String, Object> stats = new HashMap<>();

        // Fetch counts from repositories
        stats.put("totalAppointments", appointmentRepo.count());
        stats.put("pendingAppointments", appointmentRepo.countByAppointmentStatus("pending"));
        stats.put("completedRepairs", appointmentRepo.countByAppointmentStatus("completed"));
        stats.put("registeredUsers", userRepo.count());
        stats.put("availableAutoParts", autoPartRepo.count());
        stats.put("totalRevenue", appointmentRepo.getTotalRevenue());

        return stats;
    }
}
