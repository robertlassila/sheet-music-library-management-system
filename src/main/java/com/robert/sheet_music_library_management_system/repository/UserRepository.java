package com.robert.sheet_music_library_management_system.repository;

import com.robert.sheet_music_library_management_system.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
