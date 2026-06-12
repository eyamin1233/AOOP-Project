package com.redpulse.repository;

import com.redpulse.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepository
        extends JpaRepository<Event, Integer> {

    List<Event> findByBloodBankEmail(String bloodBankEmail);
}