package com.redpulse.controller;

import org.springframework.ui.Model;
import com.redpulse.entity.User;
import com.redpulse.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class DonorController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/donors")
    public String showDonors(@RequestParam(required = false) String bloodtype,
                             Model model) {

        List<User> donors;

        if (bloodtype != null && !bloodtype.isEmpty()) {
            donors = userRepository.findByRoleAndBloodtype("DONOR", bloodtype);
        } else {
            donors = userRepository.findByRole("DONOR");
        }

        model.addAttribute("donors", donors);
        return "donors";
    }
}
