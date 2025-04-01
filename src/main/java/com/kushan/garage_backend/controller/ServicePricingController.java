package com.kushan.garage_backend.controller;

import com.kushan.garage_backend.entity.ServicePricing;
import com.kushan.garage_backend.repository.ServicePricingRepository;
import com.kushan.garage_backend.service.ServicePricingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/service-pricing")
@CrossOrigin("*") // Allow frontend to access the API
public class ServicePricingController {

    private final ServicePricingRepository servicePricingRepository;
    private final ServicePricingService servicePricingService;

    public ServicePricingController(ServicePricingRepository repository, ServicePricingService servicePricingService) {
        this.servicePricingRepository = repository;
        this.servicePricingService = servicePricingService;
    }

    // 1. Add a new service pricing (Admin Side)
    @PostMapping
    public ResponseEntity<ServicePricing> addServicePricing(@RequestBody ServicePricing servicePricing) {
        return ResponseEntity.ok(servicePricingRepository.save(servicePricing));
    }

    // Update an existing service
    @PutMapping("/{id}")
    public ResponseEntity<ServicePricing> updateService(@PathVariable Long id, @RequestBody ServicePricing updatedService) {
        return servicePricingRepository.findById(id).map(service -> {
            service.setService(updatedService.getService());
            service.setEstimatedTime(updatedService.getEstimatedTime());
            service.setEstimatedPrice(updatedService.getEstimatedPrice());
            return ResponseEntity.ok(servicePricingRepository.save(service));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAutoPart(@PathVariable Long id) {
        servicePricingService.deleteService(id);
        return ResponseEntity.noContent().build();
    }

    // 2. Get all service pricing details (Admin Side)
    @GetMapping
    public ResponseEntity<List<ServicePricing>> getAllServicePricing() {
        return ResponseEntity.ok(servicePricingRepository.findAll());
    }

    // 3. Get service suggestions based on input (Customer Side)
    @GetMapping("/suggestions")
    public ResponseEntity<List<ServicePricing>> getSuggestions(@RequestParam String query) {
        return ResponseEntity.ok(servicePricingRepository.findByServiceContainingIgnoreCase(query));
    }
}
