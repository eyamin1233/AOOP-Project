package com.redpulse.controller;

import org.springframework.ui.Model;
import com.redpulse.entity.BloodBank;
import com.redpulse.entity.User;
import com.redpulse.repository.BloodBankRepository;
import com.redpulse.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    // ---------------- HOME ----------------
    @GetMapping("/")
    public String home() {
        return "index";
    }

    // ---------------- LOGIN PAGE ----------------
    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    // ---------------- REGISTER PAGE ----------------
    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {

        session.invalidate();

        return "redirect:/login";
    }

    @Autowired
    private BloodBankRepository bloodBankRepository;
    @GetMapping("/bloodbank/dashboard")
    public String bloodBankDashboard(HttpSession session, Model model) {

        User user = (User) session.getAttribute("loggedInUser");

        if (user == null || !"BLOOD_BANK".equals(user.getRole())) {
            return "redirect:/login";
        }

        BloodBank bank = bloodBankRepository.findByEmail(user.getEmail());

        model.addAttribute("user", user);

        // IMPORTANT FIX 👇 prevent null crash
        if (bank == null) {
            bank = new BloodBank();

            bank.setAPositive(0);
            bank.setANegative(0);
            bank.setBPositive(0);
            bank.setBNegative(0);
            bank.setAbPositive(0);
            bank.setAbNegative(0);
            bank.setOPositive(0);
            bank.setONegative(0);
        }

        model.addAttribute("bank", bank);

        return "bloodbank-dashboard";
    }


    @GetMapping("/inventory")
    public String inventoryPage(HttpSession session, Model model) {

        User user = (User) session.getAttribute("loggedInUser");

        if (user == null || !"BLOOD_BANK".equals(user.getRole())) {
            return "redirect:/login";
        }

        BloodBank bank = bloodBankRepository.findByEmail(user.getEmail());

        model.addAttribute("bank", bank);

        return "inventory";
    }

    @GetMapping("/chat")
    public String chatPage() {
        return "chat";
    }


    @PostMapping("/inventory/update")
    public String updateInventory(
            HttpSession session,
            @RequestParam Integer aPositive,
            @RequestParam Integer aNegative,
            @RequestParam Integer bPositive,
            @RequestParam Integer bNegative,
            @RequestParam Integer abPositive,
            @RequestParam Integer abNegative,
            @RequestParam Integer oPositive,
            @RequestParam Integer oNegative
    ) {

        User user = (User) session.getAttribute("loggedInUser");

        BloodBank bank = bloodBankRepository.findByEmail(user.getEmail());

        bank.setAPositive(aPositive);
        bank.setANegative(aNegative);
        bank.setBPositive(bPositive);
        bank.setBNegative(bNegative);
        bank.setAbPositive(abPositive);
        bank.setAbNegative(abNegative);
        bank.setOPositive(oPositive);
        bank.setONegative(oNegative);

        bloodBankRepository.save(bank);

        return "redirect:/bloodbank/dashboard";
    }

    // ---------------- REGISTER PROCESS ----------------
    @PostMapping("/register")
    public String registerUser(
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String contact,
            @RequestParam String bloodtype,
            @RequestParam String location,
            @RequestParam String password,
            @RequestParam String role
    ) {

        // 🔴 ALWAYS SAVE USER FIRST (FOR LOGIN)
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setContact(contact);
        user.setBloodtype(bloodtype);
        user.setLocation(location);
        user.setPassword(password);
        user.setRole(role);

        if ("DONOR".equals(role)) {
            user.setBloodtype(bloodtype);   // only donors have blood type
        } else {
            user.setBloodtype(null);        // blood banks/admin don't
        }

        userService.registerUser(user);

        // 🔵 IF BLOOD BANK → ALSO SAVE EXTRA DATA
        if ("BLOOD_BANK".equals(role)) {

            BloodBank bank = new BloodBank();
            bank.setName(name);
            bank.setEmail(email);
            bank.setContact(contact);
            bank.setLocation(location);

            bank.setAPositive(0);
            bank.setANegative(0);
            bank.setBPositive(0);
            bank.setBNegative(0);
            bank.setAbPositive(0);
            bank.setAbNegative(0);
            bank.setOPositive(0);
            bank.setONegative(0);

            bloodBankRepository.save(bank);
        }

        return "redirect:/login";
    }
    // ---------------- LOGIN PROCESS ----------------
    @PostMapping("/login")
    public String loginUser(@RequestParam String email,
                            @RequestParam String password,
                            HttpSession session) {

        User user = userService.login(email, password);

        if (user == null) {
            return "redirect:/login?error";
        }

        session.setAttribute("loggedInUser", user);

        if ("ADMIN".equals(user.getRole())) {
            return "redirect:/admin";
        }

        if ("BLOOD_BANK".equals(user.getRole())) {
            return "redirect:/bloodbank/dashboard";
        }

        return "redirect:/profile";
    }
}