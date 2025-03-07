package com.kushan.garage_backend.controller;

import com.kushan.garage_backend.entity.AutoPart;
import com.kushan.garage_backend.service.AutoPartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/autoParts")
public class AutoPartController {

    @Autowired
    private AutoPartService autoPartService;

    @PostMapping
    public ResponseEntity<AutoPart> addAutoPart(@RequestBody AutoPart autoPart) {
        AutoPart newPart = autoPartService.addAutoPart(
                autoPart.getName(),
                autoPart.getCategory(),
                autoPart.getPrice(),
                autoPart.getStock(),
                autoPart.getBrand(),
                autoPart.getVehicle(),
                autoPart.getImageUrl()
        );
        return ResponseEntity.ok(newPart);
    }


    @GetMapping
    public ResponseEntity<List<AutoPart>> getAllAutoParts() {
        return ResponseEntity.ok(autoPartService.getAllAutoParts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AutoPart> getAutoPartById(@PathVariable Long id) {
        AutoPart autoPart = autoPartService.getAutoPartById(id);
        if (autoPart != null) {
            return ResponseEntity.ok(autoPart);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<AutoPart> updateAutoPart(
            @PathVariable Long id,
            @RequestBody AutoPart autoPart) {

        AutoPart updatedPart = autoPartService.updateAutoPart(
                id,
                autoPart.getName(),
                autoPart.getCategory(),
                autoPart.getPrice(),
                autoPart.getStock(),
                autoPart.getBrand(),
                autoPart.getVehicle(),
                autoPart.getImageUrl()
        );

        return ResponseEntity.ok(updatedPart);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAutoPart(@PathVariable Long id) {
        autoPartService.deleteAutoPart(id);
        return ResponseEntity.noContent().build();
    }
}
