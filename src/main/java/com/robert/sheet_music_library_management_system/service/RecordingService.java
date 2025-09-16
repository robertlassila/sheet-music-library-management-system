package com.robert.sheet_music_library_management_system.service;

import com.robert.sheet_music_library_management_system.domain.Recording;
import com.robert.sheet_music_library_management_system.domain.User;
import com.robert.sheet_music_library_management_system.repository.RecordingRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecordingService {

    private final RecordingRepository recordingRepository;
    private final UserService userService;

    public RecordingService(RecordingRepository recordingRepository, UserService userService) {
        this.recordingRepository = recordingRepository;
        this.userService = userService;
    }

}
