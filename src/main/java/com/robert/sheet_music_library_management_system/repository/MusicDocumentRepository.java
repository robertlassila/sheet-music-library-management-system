package com.robert.sheet_music_library_management_system.repository;

import com.robert.sheet_music_library_management_system.domain.MusicDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MusicDocumentRepository extends JpaRepository<MusicDocument, Long> {

}
