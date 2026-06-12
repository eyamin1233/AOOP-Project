package com.redpulse.repository;

import com.redpulse.entity.Donor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DonorRepository
        extends JpaRepository<Donor, Integer> {

}