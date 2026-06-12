package com.redpulse.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "bloodbank")
public class BloodBank {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "blood_bankid")
    private Integer bloodBankId;

    @Column(name = "name")
    private String name;

    @Column(name = "location")
    private String location;

    @Column(name = "contact")
    private String contact;

    @Column(name = "email")
    private String email;

    @Column(name = "a_positive")
    private Integer aPositive;

    @Column(name = "a_negative")
    private Integer aNegative;

    @Column(name = "b_positive")
    private Integer bPositive;

    @Column(name = "b_negative")
    private Integer bNegative;

    @Column(name = "ab_positive")
    private Integer abPositive;

    @Column(name = "ab_negative")
    private Integer abNegative;

    @Column(name = "o_positive")
    private Integer oPositive;

    @Column(name = "o_negative")
    private Integer oNegative;

    // getters/setters
}