package com.redpulse.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "review_bloodbank")
public class ReviewBloodBank {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // create surrogate key if needed

    private String review;

    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name="userid")
    private User user;

    @ManyToOne
    @JoinColumn(name="BloodBankID")
    private BloodBank bloodBank;
}
