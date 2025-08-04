package com.robert.sheet_music_library_management_system.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "Hello, home";
    }

    @GetMapping("/secured")
    public String secured() {
        // This is the endpoint reached after successful login.
        // You can customize this endpoint to redirect to a main dashboard page
        // Or add logic here to redirect to another controller's endpoint if you prefer.
        return "securedPage";
    }
}