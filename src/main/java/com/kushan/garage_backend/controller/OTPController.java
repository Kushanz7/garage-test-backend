package com.kushan.garage_backend.controller;

import com.kushan.garage_backend.repository.UserRepository;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@RestController
@RequestMapping("/api")
public class OTPController {

    private final UserRepository userRepository;


    private final Map<String, String> otpStorage = new HashMap<>();

    @Value("${twilio.account.sid}")
    private String accountSid;

    @Value("${twilio.auth.token}")
    private String authToken;

    @Value("${twilio.phone.number}")
    private String twilioNumber;

    @Autowired
    public OTPController(UserRepository userRepository) {
        this.userRepository = userRepository;
        // Initialize Twilio
        Twilio.init("AC29c01439168e009858c3b0b3ca9107cb", "97b78c3c0d6331812c87b10023defd25");
    }


    @PostMapping("/send-otp")
    public Map<String, String> sendOTP(@RequestBody Map<String, String> request) {
        String contactNo = request.get("contactNo");

        // Ensure the number starts with "+"
        if (!contactNo.startsWith("+")) {
            return Map.of("success", "false", "message", "Invalid phone number format. Use E.164 format (e.g., +14155552671)");
        }

        // Generate OTP
        String otp = generateOTP();
        otpStorage.put(contactNo, otp);

        try {
            Message.creator(
                    new PhoneNumber(contactNo),   // Ensure correct format
                    new PhoneNumber(twilioNumber),
                    "Your OTP for AutoSlot is: " + otp
            ).create();

            return Map.of("success", "true", "message", "OTP sent successfully");
        } catch (Exception e) {
            return Map.of("success", "false", "message", "Failed to send OTP: " + e.getMessage());
        }
    }

    @PostMapping("/verify-otp")
    public Map<String, String> verifyOTP(@RequestBody Map<String, String> request) {
        String contactNo = request.get("contactNo");
        String otp = request.get("otp");

        if (otpStorage.containsKey(contactNo) && otpStorage.get(contactNo).equals(otp)) {
            otpStorage.remove(contactNo);
            return Map.of("success", "true", "message", "OTP verified successfully");
        } else {
            return Map.of("success", "false", "message", "Invalid OTP");
        }
    }

    private String generateOTP() {
        return String.valueOf(new Random().nextInt(900000) + 100000);
    }
}
