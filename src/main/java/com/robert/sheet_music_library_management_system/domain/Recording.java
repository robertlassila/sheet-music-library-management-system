package com.robert.sheet_music_library_management_system.domain;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Recording {

    @Id
    @GeneratedValue
    private Long id;

    private String link;
    private String artist;
    private String date;

    @ManyToOne(fetch = FetchType.LAZY)
    private MusicDocument musicDocument;

    public Recording() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public MusicDocument getMusicDocument() {
        return musicDocument;
    }

    public void setMusicDocument(MusicDocument musicDocument) {
        this.musicDocument = musicDocument;
    }
}
