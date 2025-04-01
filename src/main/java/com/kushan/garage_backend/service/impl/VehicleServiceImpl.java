package com.kushan.garage_backend.service.impl;

import com.kushan.garage_backend.entity.Vehicle;
import com.kushan.garage_backend.repository.VehicleRepository;
import com.kushan.garage_backend.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository vehicleRepository;

    @Autowired
    public VehicleServiceImpl(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    @Override
    public Vehicle addVehicle(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    @Override
    public List<Vehicle> getVehiclesByCustomerId(Long customerId) {
        return vehicleRepository.findByCustomer_Id(customerId);
    }

    @Override
    public Vehicle updateVehicle(String vehicleNumber, Vehicle updatedVehicle) {
        return vehicleRepository.findById(vehicleNumber).map(vehicle -> {
            vehicle.setColor(updatedVehicle.getColor());
            vehicle.setCurrentRange(updatedVehicle.getCurrentRange());
            vehicle.setDescription(updatedVehicle.getDescription());
            vehicle.setFuelType(updatedVehicle.getFuelType());
            vehicle.setModel(updatedVehicle.getModel());
            vehicle.setYear(updatedVehicle.getYear());
            return vehicleRepository.save(vehicle);
        }).orElse(null);
    }

    @Override
    public boolean deleteVehicle(String vehicleNumber) {
        if (vehicleRepository.existsById(vehicleNumber)) {
            vehicleRepository.deleteById(vehicleNumber);
            return true;
        }
        return false;
    }
}
