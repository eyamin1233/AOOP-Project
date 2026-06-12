package com.redpulse.controller;

import com.redpulse.entity.BloodRequest;
import com.redpulse.entity.User;
import com.redpulse.repository.BloodRequestRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class BloodRequestController {

    @Autowired
    private BloodRequestRepository bloodRequestRepository;

    // SHOW PAGE
    @GetMapping("/blood-requests")
    public String showPage(Model model) {

        List<BloodRequest> requests = bloodRequestRepository.findAll();
        model.addAttribute("requests", requests);
        model.addAttribute("newRequest", new BloodRequest());

        return "blood-requests";
    }

    // CREATE POST
    @PostMapping("/blood-requests")
    public String createRequest(@ModelAttribute BloodRequest request,
                                HttpSession session) {

        User user = (User) session.getAttribute("loggedInUser");

        if (user == null) {
            return "redirect:/login";
        }

        request.setUserId(user.getId());

        bloodRequestRepository.save(request);

        return "redirect:/blood-requests";
    }

    // SEARCH BY BLOOD TYPE
    @GetMapping("/blood-requests/search")
    public String search(@RequestParam String bloodType, Model model) {

        List<BloodRequest> requests =
                bloodRequestRepository.findByBloodType(bloodType);

        model.addAttribute("requests", requests);
        model.addAttribute("newRequest", new BloodRequest());

        return "blood-requests";
    }
    @GetMapping("/debug-session")
    @ResponseBody
    public String debug(HttpSession session) {
        return String.valueOf(session.getAttribute("loggedInUser"));
    }
}