package com.robert.sheet_music_library_management_system.repository;

import com.robert.sheet_music_library_management_system.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByGoogleId(String googleId);
}
