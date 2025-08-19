package com.robert.sheet_music_library_management_system.service;

import com.robert.sheet_music_library_management_system.domain.MusicDocument;
import com.robert.sheet_music_library_management_system.repository.MusicDocumentRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class MusicDocumentServiceTest {
    
    @Autowired
    private MusicDocumentService musicDocumentService;
    
    @Autowired
    private MusicDocumentRepository musicDocumentRepository;
    
    MusicDocument musicDocument1;
    MusicDocument musicDocument2;
    
    @BeforeEach
    void prepData() {

        musicDocument1 = new MusicDocument();
        musicDocument2 = new MusicDocument();
        musicDocument1.setTitle("Title1");
        musicDocument2.setTitle("Title2");


        musicDocument1 = musicDocumentRepository.save(musicDocument1);
        musicDocument2 = musicDocumentRepository.save(musicDocument2);
    }
    @AfterEach
    void cleanUpData() {
        musicDocumentRepository.delete(musicDocument1);
        musicDocumentRepository.delete(musicDocument2);
    }

    @Transactional
    @Test
    void testDelete() {
        MusicDocument newMusicDocument = new MusicDocument();
        musicDocumentRepository.save(newMusicDocument);
        List<MusicDocument> musicDocuments = musicDocumentRepository.findAll();
        int size1 = musicDocuments.size();
        musicDocumentService.delete(newMusicDocument);
        musicDocuments = musicDocumentRepository.findAll();
        int size2 = musicDocuments.size();

        assertEquals(size1, size2 + 1);
    }
}
