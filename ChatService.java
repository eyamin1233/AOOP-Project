package com.redpulse.service;

import org.springframework.stereotype.Service;

@Service
public class ChatService {

    public String getReply(String message) {

        if (message == null || message.trim().isEmpty()) {
            return "Please ask a question about blood donation.";
        }

        message = message.toLowerCase();

        // =========================
        // ❗ ELIGIBILITY RULES
        // =========================

        if (containsAny(message, "fever", "temperature", "sick", "ill")) {
            return " You cannot donate blood if you have fever or sickness. Wait until fully recovered (at least 7 days).";
        }

        if (containsAny(message, "cold", "flu", "cough")) {
            return " You should not donate blood during cold, flu, or infection.";
        }

        if (containsAny(message, "weight", "kg")) {
            return " Minimum weight required for blood donation is usually 50 kg.";
        }

        if (containsAny(message, "diabetes, diabetic")) {
            return " People with prediabetes and diabetes are generally eligible to donate blood, platelets and plasma if they are feeling well and their diabetes is well managed.";
        }

        if (containsAny(message, "age", "old", "years")) {
            return " Eligible age for blood donation is 18 to 60 years.";
        }

        if (containsAny(message, "diabetes", "sugar")) {
            return " Controlled diabetes may allow donation, but doctor approval is recommended.";
        }

        if (containsAny(message, "blood pressure", "bp", "hypertension")) {
            return " Blood pressure must be normal to donate blood safely.";
        }

        if (containsAny(message, "interval", "gap", "how often", "frequency")) {
            return " You should wait at least 3 months between whole blood donations.";
        }

        if (containsAny(message, "pregnant", "pregnancy", "breastfeeding")) {
            return " Pregnant or breastfeeding women cannot donate blood.";
        }

        if (containsAny(message, "tattoo", "piercing")) {
            return " You must wait 6–12 months after tattoo or piercing before donating blood.";
        }

        // =========================
        // 💉 GENERAL KNOWLEDGE
        // =========================

        if (containsAny(message, "benefit", "advantages", "why donate")) {
            return " Blood donation saves lives, improves heart health, and helps patients in emergencies.";
        }

        if (containsAny(message, "how donate", "process", "steps")) {
            return " Steps: Registration → Health check → Blood collection → Rest → Refreshments.";
        }

        if (containsAny(message, "time", "how long")) {
            return " Blood donation usually takes 8–10 minutes for collection and about 30 minutes total.";
        }

        if (containsAny(message, "pain", "hurt", "safe")) {
            return " Blood donation is safe and causes only mild discomfort like a small pinch.";
        }

        if (containsAny(message, "who cannot", "ineligible")) {
            return " People with severe illness, low hemoglobin, or infections cannot donate blood.";
        }

        // =========================
        // DEFAULT RESPONSE
        // =========================

        return "I am RedPulse Blood Assistant. Ask about eligibility, donation process, or health conditions.";
    }

    // helper method (VERY IMPORTANT)
    private boolean containsAny(String text, String... keywords) {
        for (String word : keywords) {
            if (text.contains(word)) return true;
        }
        return false;
    }
}