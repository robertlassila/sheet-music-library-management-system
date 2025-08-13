package com.robert.sheet_music_library_management_system.repository;

import com.robert.sheet_music_library_management_system.domain.MusicDocument;
import com.robert.sheet_music_library_management_system.domain.Performance;
import com.robert.sheet_music_library_management_system.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PerformanceRepository extends JpaRepository<Performance, Long> {
    List<Performance> findByUser(User user);
    List<Performance> findByMusicDocumentsContains(MusicDocument musicDocument);
}
