package com.robert.sheet_music_library_management_system.web;

import com.robert.sheet_music_library_management_system.domain.MusicDocument;
import com.robert.sheet_music_library_management_system.service.MusicDocumentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

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
        model.addAttribute("musicDocument", musicDocumentService.findById(id));
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
    public String saveNewMusicDocument(@ModelAttribute MusicDocument musicDocument) {
        musicDocumentService.save(musicDocument);
        return "redirect:/musicdocuments";
    }

    @PostMapping("/delete")
    public String deleteNewMusicDocument(@ModelAttribute MusicDocument musicDocument) {
        musicDocumentService.delete(musicDocument);
        return "redirect:/musicdocuments";
    }

}
