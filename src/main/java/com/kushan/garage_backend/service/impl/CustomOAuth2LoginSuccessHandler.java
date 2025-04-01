package com.kushan.garage_backend.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class CustomOAuth2LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, IOException {
        // Get user details from authentication
        OAuth2User oauthUser = (OAuth2User) authentication.getPrincipal();

        // Extract user info
        Map<String, Object> userDetails = new HashMap<>();
        userDetails.put("name", oauthUser.getAttribute("name"));
        userDetails.put("userId", oauthUser.getAttribute("sub")); // Change this based on your DB
        userDetails.put("email", oauthUser.getAttribute("email"));

        // Send JSON response instead of redirect
        response.setContentType("application/json");
        response.getWriter().write(objectMapper.writeValueAsString(userDetails));
    }
}
