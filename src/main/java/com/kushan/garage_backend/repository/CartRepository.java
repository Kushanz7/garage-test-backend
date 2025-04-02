package com.kushan.garage_backend.repository;

import com.kushan.garage_backend.entity.Cart;
import com.kushan.garage_backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByUserAndStatus(User user, String status);
}
