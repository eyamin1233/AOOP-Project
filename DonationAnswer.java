package com.redpulse.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "donation_answers")
public class DonationAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String health_issue;

    private String medical_conditions;

    private String medications;

    private String hospital_visits;

    private String fatigue;

    private LocalDateTime created_at;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name="request_id")
    private BloodRequest request;
}
