package com.redpulse.controller;

import com.redpulse.entity.Event;
import com.redpulse.entity.User;
import com.redpulse.repository.EventRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class EventController {

    @Autowired
    private EventRepository eventRepository;

    @GetMapping("/events")
    public String eventsPage(HttpSession session, Model model) {

        User user = (User) session.getAttribute("loggedInUser");

        // ✅ FIX: prevent null crash
        if (user == null) {
            return "redirect:/login";
        }

        model.addAttribute("user", user);

        return "events";
    }

    @PostMapping("/events/create")
    public String createEvent(
            @RequestParam String eventName,
            @RequestParam String location,
            @RequestParam String eventDate,
            @RequestParam String eventTime,
            HttpSession session) {

        User user =
                (User) session.getAttribute("loggedInUser");

        Event event = new Event();

        event.setEventName(eventName);
        event.setLocation(location);

        event.setEventDate(
                java.time.LocalDate.parse(eventDate));

        event.setEventTime(
                java.time.LocalTime.parse(eventTime));

        event.setBloodBankEmail(user.getEmail());

        eventRepository.save(event);

        return "redirect:/events";
    }

    @PostMapping("/events/delete/{id}")
    public String deleteEvent(
            @PathVariable Integer id) {

        eventRepository.deleteById(id);

        return "redirect:/events";
    }
}