package com.redpulse.controller;

import com.redpulse.entity.User;
import com.redpulse.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

@RestController
public class TestController {

    private final UserRepository userRepository;

    public TestController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/test")
    public String test() {

        User user = new User();

        user.setName("Test User");
        user.setEmail("test@gmail.com");

        userRepository.save(user);

        return "Saved!";
    }
}