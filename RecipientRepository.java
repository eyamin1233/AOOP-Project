package com.redpulse.repository;

import com.redpulse.entity.Recipient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipientRepository
        extends JpaRepository<Recipient, Integer> {

}