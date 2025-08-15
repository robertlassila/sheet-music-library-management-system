package com.robert.sheet_music_library_management_system.service;

import com.robert.sheet_music_library_management_system.domain.User;
import com.robert.sheet_music_library_management_system.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public void delete(User user) {
        userRepository.delete(user);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }


    public User findByGoogleId() {
        OAuth2User oauth2User = (OAuth2User) SecurityContextHolder.getContext()
            .getAuthentication()
            .getPrincipal();

        String googleId = oauth2User.getAttribute("sub");



        List<User> users = userRepository.findByGoogleId(googleId);
        User user = new User();
        if (!users.isEmpty()) {
            user = users.get(0);
        }
        return user;
    }

}

