package com.kushan.garage_backend.service;

import com.kushan.garage_backend.entity.AutoPart;
import java.math.BigDecimal;
import java.util.List;

public interface AutoPartService {
    AutoPart addAutoPart(String name, String category, BigDecimal price, int stock, String brand, String vehicle, String image);
    List<AutoPart> getAllAutoParts();
    AutoPart updateAutoPart(Long id, String name, String category, BigDecimal price, int stock, String brand, String vehicle, String image);
    void deleteAutoPart(Long id);
    AutoPart getAutoPartById(Long id);
}
