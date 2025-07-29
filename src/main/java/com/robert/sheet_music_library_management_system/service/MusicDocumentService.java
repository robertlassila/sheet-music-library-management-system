package com.robert.sheet_music_library_management_system.service;

import com.robert.sheet_music_library_management_system.domain.MusicDocument;
import com.robert.sheet_music_library_management_system.repository.MusicDocumentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MusicDocumentService {

    private final MusicDocumentRepository musicDocumentRepository;

    public MusicDocumentService(MusicDocumentRepository musicDocumentRepository) {
        this.musicDocumentRepository = musicDocumentRepository;
    }

    public MusicDocument save(MusicDocument musicDocument) {
        return musicDocumentRepository.save(musicDocument);
    }

    public void delete(MusicDocument musicDocument) {
        musicDocumentRepository.delete(musicDocument);
    }

    public List<MusicDocument> findAll() {
        return musicDocumentRepository.findAll();
    }

    public Optional<MusicDocument> findById(Long id) {
        return musicDocumentRepository.findById(id);
    }





}
