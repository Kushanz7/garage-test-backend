package com.kushan.garage_backend.controller;

import com.kushan.garage_backend.entity.User;
import com.kushan.garage_backend.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/api/customer")
public class UserController {

    @Autowired
    private UserService userService;

    //Build Add Customer REST API
    @PostMapping("/add")
    public String addUser(@RequestBody User user) {
        userService.createCustomer(user);

        return "success add user";
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
        boolean isUpdated = userService.updateCustomer(id, updatedUser);
        if (isUpdated) {
            return ResponseEntity.ok("User updated successfully!");
        } else {
            return ResponseEntity.status(404).body("User not found");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCustomerById(@PathVariable Long id) {
        Optional<User> customer = userService.getCustomerById(id);

        if (customer.isPresent()) {
            return ResponseEntity.ok(customer.get());
        } else {
            return ResponseEntity.status(404).body("Customer not found");
        }
    }

    @GetMapping("/employees")
    public ResponseEntity<List<User>> getAllEmployees() {
        List<User> employees = userService.getAllUsersByRole("employee");
        return ResponseEntity.ok(employees);
    }

}
