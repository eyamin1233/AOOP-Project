package com.redpulse.service;

import com.redpulse.entity.BloodBank;
import com.redpulse.repository.BloodBankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BloodBankService {

    @Autowired
    private BloodBankRepository bloodBankRepository;

    // get all blood banks
    public List<BloodBank> getAllBloodBanks() {
        return bloodBankRepository.findAll();
    }

}