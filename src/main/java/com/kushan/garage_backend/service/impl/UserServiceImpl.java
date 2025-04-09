package com.kushan.garage_backend.service.impl;

import com.kushan.garage_backend.entity.User;
import com.kushan.garage_backend.repository.UserRepository;
import com.kushan.garage_backend.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void createCustomer(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public Optional<User> getCustomerById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public boolean updateCustomer(Long id, User updatedUser) {
        Optional<User> existingUser = userRepository.findById(id);
        if (existingUser.isPresent()) {
            User user = existingUser.get();
            user.setFirstName(updatedUser.getFirstName());
            user.setLastName(updatedUser.getLastName());
            user.setEmail(updatedUser.getEmail());
            user.setContactNo(updatedUser.getContactNo());
            user.setAge(updatedUser.getAge());
            user.setGender(updatedUser.getGender());
            user.setNationality(updatedUser.getNationality());
            user.setAddress(updatedUser.getAddress());
            userRepository.save(user);
            return true;
        }
        return false;
    }

    @Override
    public List<User> getAllUsersByRole(String role) {
        return userRepository.findByRole(role);
    }

    // ✅ Upload Profile Picture
    public User uploadProfilePicture(Long userId, byte[] imageBytes) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setProfilePicture(imageBytes); // Store the byte array
            return userRepository.save(user);
        }
        throw new RuntimeException("User not found");
    }


    // ✅ Get Profile Picture
    public byte[] getProfilePicture(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        return optionalUser.map(User::getProfilePicture).orElse(null);
    }


}