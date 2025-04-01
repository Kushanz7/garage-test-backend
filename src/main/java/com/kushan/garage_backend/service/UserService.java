package com.kushan.garage_backend.service;

import com.kushan.garage_backend.entity.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface UserService {
    void createCustomer(User user);
    Optional<User> getCustomerById(Long id);
    Optional<User> findByEmail(String email);
    List<User> getAllUsersByRole(String role);
    boolean updateCustomer(Long id, User updatedUser);
    User uploadProfilePicture(Long userId, byte[] imageBytes);
    byte[] getProfilePicture(Long userId);
}
