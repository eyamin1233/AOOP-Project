package com.redpulse.controller;


import com.redpulse.service.ChatService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
public class ChatController {

    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @GetMapping("/chat/suggestions")
    public List<String> getSuggestions() {
        return List.of(
                "Can I donate blood after fever?",
                "What is the minimum weight?",
                "Who cannot donate blood?",
                "Can diabetic patients donate blood?",
                "How often can I donate blood?",
                "Can I donate blood after tattoo?",
                "What is the age limit for donation?"
        );
    }

    // ✅ ADD THIS (THIS FIXES YOUR PROBLEM)
    @PostMapping("/chat/api")
    public Map<String, String> chat(@RequestBody Map<String, String> request) {

        String message = request.get("message");

        String reply = chatService.getReply(message);

        return Map.of("reply", reply);
    }
}