package com.redpulse.controller;

import com.redpulse.entity.BloodBank;
import com.redpulse.repository.BloodBankRepository;
import com.redpulse.service.BloodBankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class BloodBankController {

    @Autowired
    private BloodBankService bloodBankService;

    @GetMapping("/bloodbanks")
    public String showBloodBanks(Model model) {

        model.addAttribute("banks", bloodBankService.getAllBloodBanks());

        return "bloodbanks";
    }
}