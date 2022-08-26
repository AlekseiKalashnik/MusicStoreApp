package project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import project.dao.AlbumDAO;
import project.dao.ArtistDAO;
import project.entity.Album;
import project.entity.Artist;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/albums")
public class AlbumController {

    private final ArtistDAO artistDAO;
    private final AlbumDAO albumDAO;

    @Autowired
    public AlbumController(ArtistDAO artistDAO, AlbumDAO albumDAO) {
        this.artistDAO = artistDAO;
        this.albumDAO = albumDAO;
    }

    @GetMapping
    public String showAllAlbums(Model model) {
        model.addAttribute("albums", albumDAO.showAllAlbums());
        return "albums/showAllAlbums";
    }

    @GetMapping
    public String showCertainAlbum(@PathVariable("id") int id, Model model, @ModelAttribute("artist") Artist artist) {
        model.addAttribute("album", albumDAO.showCertainAlbum(id));

        Optional<Artist> albumOwner = albumDAO.getAlbumOwner(id);
        if(albumOwner.isPresent()) {
            model.addAttribute("owner", albumOwner.get());
        } else {
            model.addAttribute("artists", artistDAO.showAllArtists());
        }
        return "albums/showCertainAlbum";
    }

    @GetMapping("/newAlbum")
    public String newAlbum(@ModelAttribute("album") Album album) {
        return "albums/newAlbum";
    }

    @PostMapping()
    public String createAlbum(@ModelAttribute("album") @Valid Album album, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "albums/newAlbum";
        } else {
            albumDAO.saveAlbum(album);
        }
        return "redirect:/albums";
    }

    @GetMapping("/{id}/editAlbum")
    public String editAlbum(Model model, @PathVariable("id") int id) {
        model.addAttribute("album", albumDAO.showCertainAlbum(id));
        return "albums/editAlbum";
    }

    @PatchMapping("/{id}")
    public String updateAlbum(@ModelAttribute("album") @Valid Album album, BindingResult bindingResult, @PathVariable("id") int id) {
        if(bindingResult.hasErrors()) {
            return "albums/editAlbum";
        } else {
            albumDAO.updateAlbum(id, album);
            return "redirect:/albums";
        }
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        albumDAO.deleteAlbum(id);
        return "redirect:/albums";
    }

    @PatchMapping("/{id}/releaseAlbum")
    public String releaseAlbum(@PathVariable("id") int id) {
        albumDAO.releaseAlbum(id);
        return "redirect:/albums/" + id;
    }

    @PatchMapping("/{id}/assignAlbum")
    public String assignAlbum(@PathVariable("id") int id, @ModelAttribute("album") Artist artist) {
        albumDAO.assignAlbum(id, artist);
        return "redirect:/albums/" + id;
    }






}
