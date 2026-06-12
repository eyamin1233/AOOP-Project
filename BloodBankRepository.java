package com.redpulse.repository;

import com.redpulse.entity.BloodBank;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BloodBankRepository
        extends JpaRepository<BloodBank, Integer> {

    BloodBank findByEmail(String email);
}