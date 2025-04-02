package com.kushan.garage_backend.repository;

import com.kushan.garage_backend.entity.Order;
import com.kushan.garage_backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUser(User user);
}
