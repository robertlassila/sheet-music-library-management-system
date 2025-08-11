package com.robert.sheet_music_library_management_system.domain;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Performance {

    @Id
    @GeneratedValue
    private Long id;

    private String venueName;
    private String ensembleName;
    private LocalDate date;

    @ManyToMany
    @JoinTable(
        name = "performance_music_document",
        joinColumns = @JoinColumn(name = "performance_id"),
        inverseJoinColumns = @JoinColumn(name = "music_document_id")
    )
    private Set<MusicDocument> musicDocuments = new HashSet<>();

}
