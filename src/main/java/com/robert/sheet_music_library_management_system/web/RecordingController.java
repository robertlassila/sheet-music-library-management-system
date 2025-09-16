package com.robert.sheet_music_library_management_system.web;

import com.robert.sheet_music_library_management_system.domain.MusicDocument;
import com.robert.sheet_music_library_management_system.domain.Recording;
import com.robert.sheet_music_library_management_system.domain.User;
import com.robert.sheet_music_library_management_system.service.MusicDocumentService;
import com.robert.sheet_music_library_management_system.service.RecordingService;
import com.robert.sheet_music_library_management_system.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Optional;
import java.util.Set;

@Controller
@RequestMapping("/recording")
public class RecordingController {

    private final RecordingService recordingService;
    private final UserService userService;
    private final MusicDocumentService musicDocumentService;


    public RecordingController(RecordingService recordingService, UserService userService, MusicDocumentService musicDocumentService) {
        this.recordingService = recordingService;
        this.userService = userService;
        this.musicDocumentService = musicDocumentService;
    }

    @GetMapping("/create")
    public String newRecording(Model model) {
        model.addAttribute("recording", new Recording());
        return "recording/create";
    }

    @PostMapping("/save")
    public String saveNewRecording(@ModelAttribute Recording recording) throws IOException {

        //User user = (User) userService.findByGoogleId();
        //recording.setMusicDocument(musicDocument);


        //recordingService.save(recording);

        return "redirect:/recording";
    }

}

