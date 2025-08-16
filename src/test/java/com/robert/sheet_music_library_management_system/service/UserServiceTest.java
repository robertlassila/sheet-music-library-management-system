package com.robert.sheet_music_library_management_system.service;

import com.robert.sheet_music_library_management_system.domain.User;
import com.robert.sheet_music_library_management_system.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    User user1;
    User user2;

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
    void testFindByGoogleId() {

        User newUser = userService.findByGoogleId(user1GoogleId);
        assertEquals(newUser.getName(), "John Doe");
    }

    @Transactional
    @Test
    void testFindAll() {
        List<User> users = userService.findAll();
        int firstLength = users.size();

        User newUser = new User();
        userRepository.save(newUser);

        users = userService.findAll();
        int secondLength = users.size();

        assertEquals(firstLength, secondLength - 1);
        userRepository.delete(newUser);
    }

    @Transactional
    @Test
    void testDeleteUser() {
        User newUser = new User();
        userRepository.save(newUser);

        List<User> users = userService.findAll();
        int firstLength = users.size();

        userRepository.delete(newUser);
        users = userService.findAll();
        int secondLength = users.size();

        assertEquals(firstLength, secondLength + 1);
    }


}
