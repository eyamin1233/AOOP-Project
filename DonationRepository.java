package com.redpulse.repository;

import com.redpulse.entity.Donation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DonationRepository extends JpaRepository<Donation, Integer> {

    long countByDonorId(Integer donorId);

}