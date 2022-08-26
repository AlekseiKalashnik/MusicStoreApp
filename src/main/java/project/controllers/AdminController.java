package project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.dao.ArtistDAO;
import project.entity.Artist;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final ArtistDAO artistDAO;

    @Autowired
    public AdminController(ArtistDAO artistDAO) {
        this.artistDAO = artistDAO;
    }

    @GetMapping
    public String adminPage(Model model, @ModelAttribute("artist") Artist artist) {
        model.addAttribute("artist", artist);

        return "adminPage";
    }

    @PatchMapping("/add")
    public String makeAdmin(@ModelAttribute("artist") Artist artist) {
        System.out.println(artist.getId());

        return "redirect:/artists";
    }
}
