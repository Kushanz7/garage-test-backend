package com.kushan.garage_backend.service.impl;

import com.kushan.garage_backend.repository.ServicePricingRepository;
import com.kushan.garage_backend.service.ServicePricingService;
import org.springframework.stereotype.Service;

@Service
public class ServicePricingServiceImpl implements ServicePricingService {
    private final ServicePricingRepository servicePricingRepository;

    public ServicePricingServiceImpl(ServicePricingRepository servicePricingRepository) {
        this.servicePricingRepository = servicePricingRepository;
    }

    @Override
    public void deleteService(Long id) {
        servicePricingRepository.deleteById(id);
    }
}
