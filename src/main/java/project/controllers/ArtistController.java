package project.controllers;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import project.dao.ArtistDAO;
import project.entity.Artist;

@Controller
@RequestMapping("/artists")
public class ArtistController {

    private final ArtistDAO artistDAO;

    @Autowired
    public ArtistController(ArtistDAO artistDAO) {
        this.artistDAO = artistDAO;
    }

    @GetMapping
    public String showAllArtists(Model model) {
        model.addAttribute("artistsList", artistDAO.showAllArtists());
        return "artists/showAllArtists";
    }

    @GetMapping("/{id}")
    public String showArtistById(@PathVariable("id") int id, Model model) {
        model.addAttribute("artist", artistDAO.showArtistById(id));
        return "artists/showCertainArtist";
    }

    @GetMapping("/new")
    public String newArtist(@ModelAttribute("artist") Artist artist) {
        return "artists/new";
    }

    @PostMapping
    public String create(@ModelAttribute("artist") @Valid Artist artist,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "artists/new";
        }

        artistDAO.save(artist);
        return "redirect:/artists";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("artist", artistDAO.showArtistById(id));
        return "artists/editCertainArtist";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("artist") @Valid Artist artist,
                         BindingResult bindingResult, @PathVariable("id") int id) {
        if (bindingResult.hasErrors()) {
            return "artists/editCertainArtist";
        }
        artistDAO.update(id, artist);
        return "redirect:/artists";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        artistDAO.delete(id);
        return "redirect:/artists";
    }
}
