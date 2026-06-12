package com.redpulse.controller;

import com.redpulse.entity.User;
import com.redpulse.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class AdminController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BloodBankRepository bloodBankRepository;

    @Autowired
    private BloodRequestRepository bloodRequestRepository;

    @Autowired
    private DonationRepository donationRepository;

    @GetMapping("/admin")
    public String adminDashboard(Model model) {

        model.addAttribute("donorCount", userRepository.count());

        model.addAttribute("bloodBankCount",
                bloodBankRepository.count());

        model.addAttribute("requestCount",
                bloodRequestRepository.count());

        model.addAttribute("donationCount",
                donationRepository.count());

        model.addAttribute("latestRequests",
                bloodRequestRepository.findTop5ByOrderByRequestDateDescRequestTimeDesc());

        return "admin";
    }

    @GetMapping("/admin/donors")
    public String donors(Model model) {

        List<User> donors = userRepository.findByRole("DONOR");

        model.addAttribute("donors", donors);

        return "admin-donors";
    }

    @GetMapping("/admin/bloodbanks")
    public String adminBloodBanks(Model model) {
        model.addAttribute("banks", bloodBankRepository.findAll());
        return "admin-bloodbanks";
    }

    @GetMapping("/admin/requests")
    public String adminRequests(Model model) {

        model.addAttribute("requests",
                bloodRequestRepository.findAll());

        return "admin-requests";
    }
}