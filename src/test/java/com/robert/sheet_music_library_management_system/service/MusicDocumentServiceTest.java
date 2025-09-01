package com.robert.sheet_music_library_management_system.service;

import com.robert.sheet_music_library_management_system.domain.MusicDocument;
import com.robert.sheet_music_library_management_system.domain.User;
import com.robert.sheet_music_library_management_system.repository.MusicDocumentRepository;
import com.robert.sheet_music_library_management_system.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
public class MusicDocumentServiceTest {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MusicDocumentService musicDocumentService;
    @Autowired
    private MusicDocumentRepository musicDocumentRepository;

    User user1;
    User user2;

    MusicDocument musicDocument1;
    MusicDocument musicDocument2;

    String user1GoogleId;
    String user2GoogleId;

    @BeforeEach
    void prepData() {
        user1 = new User();
        user2 = new User();
        user1.setName("John Doe");
        user1GoogleId = "googleId1";
        user2GoogleId = "googleId2";

        user1.setGoogleId(user1GoogleId);
        user2.setGoogleId(user2GoogleId);

        user1 = userRepository.save(user1);
        user2 = userRepository.save(user2);
    }
    @AfterEach
    void cleanUpData() {
        userRepository.delete(user1);
        userRepository.delete(user2);
    }

    @Transactional
    @Test
    void testSetDateTimeOfCreation() {

        MusicDocument musicDocument1 = new MusicDocument();
        musicDocumentService.save(musicDocument1);
        LocalDateTime now = LocalDateTime.now();
        assertTrue(musicDocument1.getDateTimeOfEntry().isBefore(now));

        musicDocumentService.save(musicDocument1); //making sure service logic makes it not update
        assertTrue(musicDocument1.getDateTimeOfEntry().isBefore(now));
    }


}
