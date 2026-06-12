package com.redpulse.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "recipient")
public class Recipient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String recipient_name;

    private String bloodtype;

    private String contact;

    private String location;

    private LocalDate received_date;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name="request_id")
    private BloodRequest request;
}
