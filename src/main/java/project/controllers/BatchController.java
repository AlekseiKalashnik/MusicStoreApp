package project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import project.dao.ArtistDAO;

@Controller
@RequestMapping("/batch-update")
public class BatchController {

    private final ArtistDAO artistDAO;

    @Autowired
    public BatchController(ArtistDAO artistDAO) {
        this.artistDAO = artistDAO;
    }

    @GetMapping
    public String showAllArtists() {
        return "batch/showAllArtists";
    }

    @GetMapping("/withoutBatch")
    public String withoutBatch() {
        artistDAO.testMultipleUpdate();
        return "redirect:/artists";
    }

    @GetMapping("/withBatch")
    public String withBatch() {
        artistDAO.testBatchUpdate();
        return "redirect:/artists";
    }
}
