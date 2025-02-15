package com.kushan.garage_backend.service;


import com.kushan.garage_backend.entity.Vehicle;

import java.util.List;

public interface VehicleService {
    Vehicle addVehicle(Vehicle vehicle);
    List<Vehicle> getVehiclesByCustomerId(Long customerId);
}
