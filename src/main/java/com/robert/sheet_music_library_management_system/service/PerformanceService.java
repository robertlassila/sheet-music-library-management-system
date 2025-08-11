package com.robert.sheet_music_library_management_system.service;

import com.robert.sheet_music_library_management_system.domain.Performance;
import com.robert.sheet_music_library_management_system.domain.User;
import com.robert.sheet_music_library_management_system.repository.PerformanceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PerformanceService {

    private final PerformanceRepository performanceRepository;
    private final UserService userService;

    public PerformanceService(PerformanceRepository performanceRepository, UserService userService) {
        this.performanceRepository = performanceRepository;
        this.userService = userService;
    }

    public Performance save(Performance performance) {
        return performanceRepository.save(performance);
    }

    public void delete(Performance performance) {
        performanceRepository.delete(performance);
    }

    public List<Performance> findAll() {
        return performanceRepository.findAll();
    }

    public Optional<Performance> findById(Long id) {
        return performanceRepository.findById(id);
    }




}
