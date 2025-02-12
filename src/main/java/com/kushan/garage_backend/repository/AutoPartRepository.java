package com.kushan.garage_backend.repository;

import com.kushan.garage_backend.entity.AutoPart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AutoPartRepository extends JpaRepository<AutoPart, Long> {
}
