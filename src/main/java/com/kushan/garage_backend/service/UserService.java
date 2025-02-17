package com.kushan.garage_backend.service;

import com.kushan.garage_backend.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    void createCustomer(User user);
    Optional<User> getCustomerById(Long id);
    Optional<User> findByEmail(String email);
    List<User> getAllUsersByRole(String role);
    boolean updateCustomer(Long id, User updatedUser);
}
