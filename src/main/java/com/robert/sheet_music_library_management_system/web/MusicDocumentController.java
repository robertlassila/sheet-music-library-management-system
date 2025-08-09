package com.robert.sheet_music_library_management_system.web;

import com.nimbusds.jose.util.Resource;
import com.robert.sheet_music_library_management_system.domain.MusicDocument;
import com.robert.sheet_music_library_management_system.domain.User;
import com.robert.sheet_music_library_management_system.service.MusicDocumentService;
import com.robert.sheet_music_library_management_system.service.UserService;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Controller
@RequestMapping("/musicdocuments")
public class MusicDocumentController {

    private final MusicDocumentService musicDocumentService;
    private final UserService userService;


    public MusicDocumentController(MusicDocumentService musicDocumentService, UserService userService) {
        this.musicDocumentService = musicDocumentService;
        this.userService = userService;
    }

    @GetMapping("")
    public String listOfMusicDocuments(Model model) {

        User user = (User) userService.findByGoogleId(getSessionUserGoogleId());

        model.addAttribute("musicDocuments", musicDocumentService.findByUser(user));
        return "musicdocuments/read";
    }

    @GetMapping("/{id}")
    public String singleMusicDocument(Model model, @PathVariable Long id) {
        MusicDocument doc = musicDocumentService.findById(id)
        .orElseThrow(() -> new RuntimeException("Document not found"));
        model.addAttribute("musicDocument", doc);
        System.out.println(doc.getPdfFile().length);
        return "musicdocuments/viewsingle";
    }

    @GetMapping("/create")
    public String newMusicDocument(Model model) {
        model.addAttribute("musicDocument", new MusicDocument());
        return "musicdocuments/create";
    }

    @GetMapping("/update/{id}")
    public String fetch(ModelMap model, @PathVariable Long id) {
        musicDocumentService.findById(id).ifPresent(doc -> model.addAttribute("musicDocument", doc));

        return "musicdocuments/update";
    }

    @PostMapping("/save")
    public String saveNewMusicDocument(@ModelAttribute MusicDocument musicDocument,
                                   @RequestParam("file") MultipartFile file) throws IOException {
    if (!file.isEmpty()) {
        musicDocument.setPdfFile(file.getBytes());
    }

    User user = (User) userService.findByGoogleId(getSessionUserGoogleId());
    musicDocument.setUser(user);
    musicDocumentService.save(musicDocument);

    return "redirect:/musicdocuments";
}

    @PostMapping("/update/{id}")
    public String updateMusicDocument(@PathVariable Long id,
                                  @ModelAttribute MusicDocument musicDocument) {
    Optional<MusicDocument> existing = musicDocumentService.findById(id);
    if (existing.isPresent()) {
        MusicDocument doc = existing.get();
        
        doc.setTitle(musicDocument.getTitle());
        doc.setComposer(musicDocument.getComposer());
        doc.setArranger(musicDocument.getArranger());
        doc.setEnsemble(musicDocument.getEnsemble());
        doc.setGenre(musicDocument.getGenre());

        musicDocumentService.save(doc);
    }
    return "redirect:/musicdocuments";
}

    @PostMapping("/delete")
    public String deleteNewMusicDocument(@ModelAttribute MusicDocument musicDocument) {
        musicDocumentService.delete(musicDocument);
        return "redirect:/musicdocuments";
    }

    @GetMapping("/{id}/pdf")
    public ResponseEntity<byte[]> getMusicDocumentPdf(@PathVariable Long id) {
    MusicDocument musicDocument = musicDocumentService.findById(id)
            .orElseThrow(() -> new RuntimeException("Sheet music not found"));

    byte[] pdfBytes = musicDocument.getPdfFile();

    return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION,
                    "inline; filename=\"" + musicDocument.getTitle().replaceAll("\\s+", "_") + ".pdf\"")
            .contentType(MediaType.APPLICATION_PDF)
            .body(pdfBytes);
    }

    public String getSessionUserGoogleId() {
        OAuth2User oauth2User = (OAuth2User) SecurityContextHolder.getContext()
            .getAuthentication()
            .getPrincipal();

    String googleId = oauth2User.getAttribute("sub");
    return googleId;
    }
}
