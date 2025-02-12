package com.kushan.garage_backend.service.impl;

import com.kushan.garage_backend.entity.AutoPart;
import com.kushan.garage_backend.repository.AutoPartRepository;
import com.kushan.garage_backend.service.AutoPartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AutoPartServiceImpl implements AutoPartService {

    @Autowired
    private AutoPartRepository autoPartRepository;

    @Override
    public AutoPart addAutoPart(String name, String category, BigDecimal price, int stock, String brand, String vehicle, String imageUrl) {
        AutoPart part = new AutoPart();
        part.setName(name);
        part.setCategory(category);
        part.setPrice(price);
        part.setStock(stock);
        part.setBrand(brand);
        part.setVehicle(vehicle);
        part.setImageUrl(imageUrl); // Store the Google Drive link
        part.setAddedOn(LocalDateTime.now());

        return autoPartRepository.save(part);
    }

    @Override
    public List<AutoPart> getAllAutoParts() {
        return autoPartRepository.findAll();
    }

    @Override
    public AutoPart getAutoPartById(Long id) {
        return autoPartRepository.findById(id).orElse(null);
    }

    @Override
    public AutoPart updateAutoPart(Long id, String name, String category, BigDecimal price, int stock, String brand, String vehicle, String imageUrl) {
        Optional<AutoPart> existingPart = autoPartRepository.findById(id);
        if (existingPart.isPresent()) {
            AutoPart part = existingPart.get();
            part.setName(name);
            part.setCategory(category);
            part.setPrice(price);
            part.setStock(stock);
            part.setBrand(brand);
            part.setVehicle(vehicle);
            part.setImageUrl(imageUrl);
            return autoPartRepository.save(part);
        }
        throw new RuntimeException("Auto Part not found with ID: " + id);
    }

    @Override
    public void deleteAutoPart(Long id) {
        if (autoPartRepository.existsById(id)) {
            autoPartRepository.deleteById(id);
        } else {
            throw new RuntimeException("Auto Part not found with ID: " + id);
        }
    }
}
