package com.kushan.garage_backend.service;


import com.kushan.garage_backend.entity.Vehicle;

import java.util.List;
import java.util.Optional;

public interface VehicleService {
    Vehicle addVehicle(Vehicle vehicle);
    List<Vehicle> getVehiclesByCustomerId(Long customerId);
    Vehicle updateVehicle(String vehicleNumber, Vehicle updatedVehicle);
    boolean deleteVehicle(String vehicleNumber);

}
