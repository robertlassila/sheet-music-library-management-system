package com.robert.sheet_music_library_management_system.domain;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue
    private Long id;

    private String googleId;
    private String email;
    private String name;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MusicDocument> musicDocuments = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Performance> performances = new ArrayList<>();

    public void addPerformance(Performance doc) {
        performances.add(doc);
        doc.setUser(this);
    }

    public void removePerformance(Performance doc) {
        performances.remove(doc);
        doc.setUser(null);
    }

    public void addMusicDocument(MusicDocument doc) {
        musicDocuments.add(doc);
        doc.setUser(this);
    }

    public void removeMusicDocument(MusicDocument doc) {
        musicDocuments.remove(doc);
        doc.setUser(null);
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGoogleId() {
        return googleId;
    }

    public void setGoogleId(String googleId) {
        this.googleId = googleId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
