package com.redpulse.repository;

import com.redpulse.entity.BloodRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BloodRequestRepository extends JpaRepository<BloodRequest, Integer> {

    List<BloodRequest> findByBloodType(String bloodType);

    List<BloodRequest> findTop5ByOrderByRequestDateDescRequestTimeDesc();


}