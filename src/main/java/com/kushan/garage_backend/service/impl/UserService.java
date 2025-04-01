package com.kushan.garage_backend.service.impl;

import com.kushan.garage_backend.enums.AuthProvider;
import com.kushan.garage_backend.repository.UserRepository;
import com.kushan.garage_backend.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserRepository usersRepository;
    private final PasswordEncoder passwordEncoder;

    public User registerUserLocal(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setAuthProvide(AuthProvider.LOCAL);
        return  usersRepository.save(user);

    }

    public User loginUserLocal(User user){
        User existingUser = usersRepository.findByEmail(user.getEmail()).orElse(null);
        if (existingUser != null){
            if (!passwordEncoder.matches(user.getPassword(), existingUser.getPassword())) {
                throw new RuntimeException("User password does  not match");
            }
            return existingUser;
        }
        throw new RuntimeException("User not found");
    }

    public  User loginRegisterByGoogleOAuth2(OAuth2AuthenticationToken auth2AuthenticationToken){

        OAuth2User oAuth2User = auth2AuthenticationToken.getPrincipal();
        String email = oAuth2User.getAttribute("email");
        String firstname = oAuth2User.getAttribute("given_name");
        String lastname = oAuth2User.getAttribute("family_name");

        log.info("USER Email FROM GOOGLE  IS {}",email );
        log.info("USER firstname from GOOGLE IS {}",firstname );
        log.info("USER lastname from GOOGLE IS {}",lastname );

        User user = usersRepository.findByEmail(email).orElse(null);
        if (user == null) {
            user = new User();
            user.setFirstName(firstname);
            user.setLastName(lastname);
            user.setEmail(email);
            user.setAuthProvide(AuthProvider.GOOGLE);
            return usersRepository.save(user);
        }
        return user;
    }

    public User findOrCreateGoogleUser(String email, String firstName, String lastName) {
        Optional<User> existingUser = usersRepository.findByEmail(email);
        return existingUser.orElseGet(() -> {
            User newUser = new User();
            newUser.setEmail(email);
            newUser.setFirstName(firstName);
            newUser.setLastName(lastName);
            newUser.setPassword(""); // No password required for Google users
            return usersRepository.save(newUser);
        });
    }
}
