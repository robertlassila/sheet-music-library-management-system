package com.robert.sheet_music_library_management_system.repository;

import com.robert.sheet_music_library_management_system.domain.Performance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PerformanceRepository extends JpaRepository<Performance, Long> {
}
