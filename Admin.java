package com.redpulse.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "admin")
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="AdminID")
    private Integer adminId;

    @Column(name="UserName")
    private String userName;

    @Column(name="Password")
    private String password;

    @Column(name="Email")
    private String email;
}