package com.kushan.garage_backend.repository;

import com.kushan.garage_backend.entity.ServicePricing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ServicePricingRepository extends JpaRepository<ServicePricing, Long> {
    List<ServicePricing> findByServiceContainingIgnoreCase(String service);
}
