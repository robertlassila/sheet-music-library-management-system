package com.robert.sheet_music_library_management_system.service;

import com.robert.sheet_music_library_management_system.domain.MusicDocument;
import com.robert.sheet_music_library_management_system.domain.User;
import com.robert.sheet_music_library_management_system.dto.MusicDocumentDTO;
import com.robert.sheet_music_library_management_system.repository.MusicDocumentRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class MusicDocumentService {

    private final MusicDocumentRepository musicDocumentRepository;
    private final UserService userService;

    public MusicDocumentService(MusicDocumentRepository musicDocumentRepository, UserService userService) {
        this.musicDocumentRepository = musicDocumentRepository;
        this.userService = userService;
    }

    public MusicDocument save(MusicDocument musicDocument) {
        if (musicDocument.getDateTimeOfEntry() == null) {
            musicDocument.setDateTimeOfEntry();
        }
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

    public List<MusicDocument> findByUser(User user) {
        return musicDocumentRepository.findByUser(user);
    }

    public List<MusicDocumentDTO> findByUserAsDTOs(User user) {
        List<MusicDocumentDTO> dtos = new ArrayList<>();
        List<MusicDocument> musicDocuments = findByUser(user);

        for (MusicDocument musicDocument : musicDocuments) {
            save(musicDocument);
        }
        musicDocuments.sort(Comparator.comparing(MusicDocument::getDateTimeOfEntry));

        for (MusicDocument musicDocument : musicDocuments) {
            MusicDocumentDTO dto = new MusicDocumentDTO();
            dto.setId(musicDocument.getId());
            dto.setComposer(musicDocument.getComposer());
            dto.setGenre(musicDocument.getGenre());
            dto.setTitle(musicDocument.getTitle());
            dto.setEnsemble(musicDocument.getEnsemble());
            dtos.add(dto);
        }

        return dtos;
    }

    public String makeTitleFromFileName(String fileName) {

        String baseName = fileName.replaceFirst("[.][^.]+$", "");
        return baseName;
    }

    public Long getUserStorage(User user) {
        Long userStorage = 0L;
        List<MusicDocument> userList = musicDocumentRepository.findByUser(user);

        for (MusicDocument musicDocument : userList) {
            byte[] pdfFile = musicDocument.getPdfFile();
            userStorage = (userStorage + pdfFile.length);
        }

        return userStorage;
    }



}
