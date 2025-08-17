package com.robert.sheet_music_library_management_system.domain;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class MusicDocument {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String composer;
    private String arranger;
    private String orchestrator;
    private Boolean isCollection;
    private Boolean hasParts;
    private Boolean hasScore;
    private String ensemble;
    private String genre;
    private String notesAboutDocument;

    @Lob
    @Column(length = 10485760)
    private byte[] pdfFile;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany
    @JoinTable(
    name = "music_document_performance",
    joinColumns = @JoinColumn(name = "music_document_id"),
    inverseJoinColumns = @JoinColumn(name = "performance_id"))
    private Set<Performance> performances = new HashSet<>();

    public MusicDocument(Long id, String title, String composer, String arranger, Boolean isCollection, Boolean hasParts, Boolean hasScore, String ensemble, String genre, String notesAboutDocument, User user) {
        this.id = id;
        this.title = title;
        this.composer = composer;
        this.arranger = arranger;
        this.orchestrator = orchestrator;
        this.isCollection = isCollection;
        this.hasParts = hasParts;
        this.hasScore = hasScore;
        this.ensemble = ensemble;
        this.genre = genre;
        this.notesAboutDocument = notesAboutDocument;
        this.user = user;
    }

    public MusicDocument() {
    }

    public Set<Performance> getPerformances() {
        return performances;
    }

    public void setPerformances(Set<Performance> performances) {
        this.performances = performances;
    }

    public String getOrchestrator() {
        return orchestrator;
    }

    public void setOrchestrator(String orchestrator) {
        this.orchestrator = orchestrator;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getComposer() {
        return composer;
    }

    public void setComposer(String composer) {
        this.composer = composer;
    }

    public String getArranger() {
        return arranger;
    }

    public void setArranger(String arranger) {
        this.arranger = arranger;
    }

    public Boolean getIsCollection() {
        return isCollection;
    }

    public void setIsCollection(Boolean isCollection) {
        this.isCollection = isCollection;
    }

    public Boolean getHasParts() {
        return hasParts;
    }

    public void setHasParts(Boolean hasParts) {
        this.hasParts = hasParts;
    }

    public Boolean getHasScore() {
        return hasScore;
    }

    public void setHasScore(Boolean hasScore) {
        this.hasScore = hasScore;
    }

    public String getEnsemble() {
        return ensemble;
    }

    public void setEnsemble(String ensemble) {
        this.ensemble = ensemble;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getNotesAboutDocument() {
        return notesAboutDocument;
    }

    public void setNotesAboutDocument(String notesAboutDocument) {
        this.notesAboutDocument = notesAboutDocument;
    }

    public byte[] getPdfFile() {
    return pdfFile;
    }

    public void setPdfFile(byte[] pdfFile) {
        this.pdfFile = pdfFile;
    }


    @Override
    public String toString() {
        return "MusicDocument{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", composer='" + composer + '\'' +
                ", arranger='" + arranger + '\'' +
                ", isCollection=" + isCollection +
                ", hasParts=" + hasParts +
                ", hasScore=" + hasScore +
                ", ensemble='" + ensemble + '\'' +
                ", genre='" + genre + '\'' +
                ", notesAboutDocument='" + notesAboutDocument + '\'' +
                '}';
    }
}
