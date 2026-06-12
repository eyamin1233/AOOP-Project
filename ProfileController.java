package com.redpulse.controller;

import com.redpulse.entity.User;
import com.redpulse.repository.DonationRepository;
import com.redpulse.repository.EventRepository;
import com.redpulse.repository.UserRepository;
import com.redpulse.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.LocalDate;

@Controller
public class ProfileController {

    @Autowired
    private DonationRepository donationRepository;

    @Autowired
    private UserService userService;
    @Autowired
    private EventRepository eventRepository;
    @GetMapping("/profile")
    public String profile(HttpSession session, Model model) {

        User user = (User) session.getAttribute("loggedInUser");

        if (user == null) {
            return "redirect:/login";
        }

        model.addAttribute("user", user);

        model.addAttribute("events", eventRepository.findAll());

        LocalDate nextEligibleDate = null;

        if (user.getLastdonationdate() != null) {
            nextEligibleDate = user.getLastdonationdate().plusDays(90);
        }

        model.addAttribute("nextEligibleDate", nextEligibleDate);

        long totalDonations = donationRepository.countByDonorId(user.getId());

        String badge;

        if(totalDonations >= 50){
            badge = "🏆 Legend Donor";
        }
        else if(totalDonations >= 20){
            badge = "🥇 Gold Donor";
        }
        else if(totalDonations >= 10){
            badge = "🥈 Silver Donor";
        }
        else if(totalDonations >= 5){
            badge = "🥉 Bronze Donor";
        }
        else if(totalDonations >= 1){
            badge = " First Time Donor";
        }
        else{
            badge = " New Member";
        }

        model.addAttribute("totalDonations", totalDonations);
        model.addAttribute("badge", badge);
        return "profile";
    }



    @Autowired
    private UserRepository userRepository;

    // Show edit page
    @GetMapping("/profile/edit")
    public String editProfile(HttpSession session, Model model) {

        User user = (User) session.getAttribute("loggedInUser");

        if (user == null) {
            return "redirect:/login";
        }

        model.addAttribute("user", user);

        return "edit-profile";
    }



    // Handle update
    @PostMapping("/profile/edit")
    public String editProfile(@ModelAttribute User formUser,
                              @RequestParam(value = "profileImage", required = false) MultipartFile file,
                              HttpSession session) {

        User user = (User) session.getAttribute("loggedInUser");

        if (user == null) {
            return "redirect:/login";
        }

        // UPDATE NORMAL FIELDS
        user.setName(formUser.getName());
        user.setContact(formUser.getContact());
        user.setLocation(formUser.getLocation());
        user.setBloodtype(formUser.getBloodtype());
        user.setLastdonationdate(formUser.getLastdonationdate());
        user.setEmail(formUser.getEmail());

        // ✅ PROFILE IMAGE UPLOAD LOGIC
        if (file != null && !file.isEmpty()) {

            try {
                String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();

                String uploadDir = System.getProperty("user.dir") + File.separator + "uploads";

                File dir = new File(uploadDir);

                if (!dir.exists()) {
                    dir.mkdirs();
                }

                File destination = new File(dir, fileName);

                file.transferTo(destination);

                user.setProfilePicture(fileName);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        userRepository.save(user);

        // update session
        session.setAttribute("loggedInUser", user);

        return "redirect:/profile";
    }


}