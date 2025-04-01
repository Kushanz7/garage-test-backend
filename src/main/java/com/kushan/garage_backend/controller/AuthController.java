package com.kushan.garage_backend.controller;

import com.kushan.garage_backend.entity.User;
import com.kushan.garage_backend.service.impl.UserService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URI;

import java.util.HashMap;


import java.util.Map;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private  final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody @Validated User user){
        return ResponseEntity.ok(userService.registerUserLocal(user));
    }

    @PostMapping("/login/local")
    public ResponseEntity<User> loginLocal(@RequestBody User user){
        return ResponseEntity.ok(userService.loginUserLocal(user));
    }

    @GetMapping("/login/google")
    public ResponseEntity<String > loginGoogleAuth(HttpServletResponse response) throws IOException {
        response.sendRedirect("/oauth2/authorization/google");
        return ResponseEntity.ok("Redirecting ..");
    }

    @GetMapping("/loginSuccess")
    public ResponseEntity<Void> handleGoogleSuccess(OAuth2AuthenticationToken token) {
        User user = userService.loginRegisterByGoogleOAuth2(token);

        URI deepLinkUri = URI.create("myapp://oauthSuccess?userId=" + user.getId() +
                "&email=" + user.getEmail() +
                "&name=" + user.getFirstName());

        return ResponseEntity.status(HttpStatus.FOUND).location(deepLinkUri).build();
    }

    // 🔹 New endpoint to handle frontend Google login request
    @PostMapping("/login/google")
    public ResponseEntity<User> handleGoogleLogin(@RequestBody Map<String, Object> googleUserData) {
        String email = (String) googleUserData.get("email");
        String firstName = (String) googleUserData.get("firstName");
        String lastName = (String) googleUserData.get("lastName");

        User user = userService.findOrCreateGoogleUser(email, firstName, lastName);
        return ResponseEntity.ok(user);

    // 🔹 New endpoint to handle frontend Google login request
    @PostMapping("/login/google")
    public ResponseEntity<User> handleGoogleLogin(@RequestBody Map<String, Object> googleUserData) {
        String email = (String) googleUserData.get("email");
        String firstName = (String) googleUserData.get("firstName");
        String lastName = (String) googleUserData.get("lastName");

        User user = userService.findOrCreateGoogleUser(email, firstName, lastName);
        return ResponseEntity.ok(user);

    }

    }

}
