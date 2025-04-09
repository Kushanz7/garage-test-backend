package com.kushan.garage_backend.entity;

import com.fasterxml.jackson.databind.DatabindException;
import com.kushan.garage_backend.enums.AuthProvider;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int age;
    private String contactNo;
    private LocalDateTime dateJoin;
    private String email;
    private String firstName;
    private String gender;
    private String lastName;
    private String nationality;
    private String nic;
    private String password;
    private String role = "CUSTOMER";
    private String address;
    @Enumerated(EnumType.STRING)
    private AuthProvider authProvide;

    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] profilePicture;
}
