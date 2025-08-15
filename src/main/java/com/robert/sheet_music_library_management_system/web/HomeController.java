package com.robert.sheet_music_library_management_system.web;

import com.robert.sheet_music_library_management_system.domain.User;
import com.robert.sheet_music_library_management_system.repository.UserRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


import com.robert.sheet_music_library_management_system.domain.User;
import com.robert.sheet_music_library_management_system.repository.UserRepository;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final UserRepository userRepository;

    public HomeController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/secured")
    public String secured(@AuthenticationPrincipal OAuth2User principal) {
        String googleId = principal.getAttribute("sub");  // Google's unique user ID
        String email = principal.getAttribute("email");
        String name = principal.getAttribute("name");

        if (userRepository.findByGoogleId(googleId).isEmpty()) {
            User newUser = new User();
            newUser.setGoogleId(googleId);
            newUser.setEmail(email);
            newUser.setName(name);
            userRepository.save(newUser);
        }
        return "redirect:/musicdocuments";
    }
}