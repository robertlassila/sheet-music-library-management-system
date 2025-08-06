package com.robert.sheet_music_library_management_system.web;

import com.nimbusds.jose.util.Resource;
import com.robert.sheet_music_library_management_system.domain.MusicDocument;
import com.robert.sheet_music_library_management_system.service.MusicDocumentService;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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


    public MusicDocumentController(MusicDocumentService musicDocumentService) {
        this.musicDocumentService = musicDocumentService;
    }

    @GetMapping("")
    public String listOfMusicDocuments(Model model) {
        model.addAttribute("musicDocuments", musicDocumentService.findAll());
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
        Optional<MusicDocument> musicDocument = musicDocumentService.findById(id);

        model.put("musicDocument", musicDocument);
        return "musicdocuments/update";
    }

    @PostMapping("/save")
    public String saveNewMusicDocument(@ModelAttribute MusicDocument musicDocument,
                                @RequestParam("file") MultipartFile file) throws IOException {
        if (!file.isEmpty()) {
        musicDocument.setPdfFile(file.getBytes()); // set your byte[] field
    }
        musicDocumentService.save(musicDocument);
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
            .contentType(MediaType.APPLICATION_PDF)
            .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"musicDocument.pdf\"")
            .body(pdfBytes);
    }
}
