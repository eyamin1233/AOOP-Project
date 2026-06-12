package com.redpulse.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Table(name = "user")
public class User {

    @Column(name = "role")
    private String role; // DONOR, BLOOD_BANK, ADMIN

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String email;
    private String contact;
    private String password;
    @Column(name = "bloodtype", nullable = true)
    private String bloodtype;
    private LocalDate lastdonationdate;
    private String location;
    private String profilePicture;
    private String otp_code;
    private LocalDateTime otp_expiry;
    private Boolean is_verified;

    // GETTERS

    // SETTERS

}