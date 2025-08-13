package com.robert.sheet_music_library_management_system.web;

import com.robert.sheet_music_library_management_system.domain.Performance;
import com.robert.sheet_music_library_management_system.domain.User;
import com.robert.sheet_music_library_management_system.service.PerformanceService;
import com.robert.sheet_music_library_management_system.service.UserService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Controller
@RequestMapping("/performance")
public class PerformanceController {

    private final PerformanceService performanceService;
    private final UserService userService;


    public PerformanceController(PerformanceService performanceService, UserService userService) {
        this.performanceService = performanceService;
        this.userService = userService;
    }

    @GetMapping("")
    public String listOfPerformances(Model model) {

        User user = (User) userService.findByGoogleId(userService.getSessionUserGoogleId());

        model.addAttribute("performances", performanceService.findByUser(user));
        return "performance/read";
    }

    @GetMapping("/{id}")
    public String singlePerformance(Model model, @PathVariable Long id) {
        Performance doc = performanceService.findById(id)
                .orElseThrow(() -> new RuntimeException("Document not found"));
        model.addAttribute("performance", doc);
        return "performance/viewsingle";
    }

    @GetMapping("/create")
    public String newPerformance(Model model) {
        model.addAttribute("performance", new Performance());
        return "performance/create";
    }

    @GetMapping("/update/{id}")
    public String fetch(ModelMap model, @PathVariable Long id) {
        performanceService.findById(id).ifPresent(doc -> model.addAttribute("performance", doc));

        return "performance/update";
    }

    @PostMapping("/save")
    public String saveNewPerformance(@ModelAttribute Performance performance) throws IOException {

        User user = (User) userService.findByGoogleId(userService.getSessionUserGoogleId());
        performance.setUser(user);
        performanceService.save(performance);

        return "redirect:/musicdocuments";
    }

    @PostMapping("/update/{id}")
    public String updatePerformance(@PathVariable Long id,
                                    @ModelAttribute Performance performance) {
        Optional<Performance> existing = performanceService.findById(id);
        if (existing.isPresent()) {
            Performance doc = existing.get();


            performanceService.save(doc);
        }
        return "redirect:/performance";
    }

    @PostMapping("/delete")
    public String deleteNewPerformance(@ModelAttribute Performance performance) {
        performanceService.delete(performance);
        return "redirect:/performance";
    }



//    @GetMapping("/{id}/pdf")
//    public ResponseEntity<byte[]> getPerformancePdf(@PathVariable Long id) {
//        Performance performance = performanceService.findById(id)
//                .orElseThrow(() -> new RuntimeException("Sheet music not found"));


//    public String getSessionUserGoogleId() {
//        OAuth2User oauth2User = (OAuth2User) SecurityContextHolder.getContext()
//            .getAuthentication()
//            .getPrincipal();
//
//        String googleId = oauth2User.getAttribute("sub");
//        return googleId;
//    }
    }

