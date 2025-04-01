package com.kushan.garage_backend.controller;

import com.kushan.garage_backend.entity.Vehicle;
import com.kushan.garage_backend.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {

    private final VehicleService vehicleService;

    @Autowired
    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addVehicle(@RequestBody Vehicle vehicle) {
        if (vehicle.getVehicleNumber() == null || vehicle.getVehicleNumber().isEmpty()) {
            return ResponseEntity.badRequest().body("Vehicle number is required");
        }
        vehicleService.addVehicle(vehicle);
        return ResponseEntity.ok("Vehicle added successfully");
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<Vehicle>> getVehiclesByCustomer(@PathVariable Long customerId) {
        List<Vehicle> vehicles = vehicleService.getVehiclesByCustomerId(customerId);
        return ResponseEntity.ok(vehicles);
    }

    // ✅ Update Vehicle
    @PutMapping("/update/{vehicleNumber}")
    public ResponseEntity<?> updateVehicle(@PathVariable String vehicleNumber, @RequestBody Vehicle updatedVehicle) {
        Vehicle vehicle = vehicleService.updateVehicle(vehicleNumber, updatedVehicle);
        if (vehicle != null) {
            return ResponseEntity.ok(vehicle);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{vehicleNumber}")
    public ResponseEntity<?> deleteVehicle(@PathVariable String vehicleNumber) {
        boolean deleted = vehicleService.deleteVehicle(vehicleNumber);
        if (deleted) {
            return ResponseEntity.ok("Vehicle deleted successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
