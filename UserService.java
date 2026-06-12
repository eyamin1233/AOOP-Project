package com.redpulse.service;

import com.redpulse.entity.User;
import com.redpulse.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void registerUser(User user) {
        userRepository.save(user);
    }

    public void updateUser(User user) {
        userRepository.save(user);
    }

    public User login(String email, String password) {

        System.out.println("Trying login with: " + email);

        User user = userRepository.findByEmail(email);

        System.out.println("User found: " + user);

        if (user != null) {
            System.out.println("DB Password: " + user.getPassword());
            System.out.println("Entered Password: " + password);
        }

        if (user != null &&
                user.getPassword().equals(password)) {

            System.out.println("LOGIN SUCCESS");

            return user;
        }

        System.out.println("LOGIN FAILED");

        return null;
    }

    public List<User> getAllDonors() {
        return userRepository.findAll();
    }
}
