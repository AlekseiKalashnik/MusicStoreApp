package project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import project.dao.AlbumDAO;
import project.dao.VisitorDAO;
import project.entity.Album;
import project.entity.Visitor;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/albums")
public class AlbumController {

    private final VisitorDAO visitorDAO;
    private final AlbumDAO albumDAO;

    @Autowired
    public AlbumController(VisitorDAO visitorDAO, AlbumDAO albumDAO) {
        this.visitorDAO = visitorDAO;
        this.albumDAO = albumDAO;
    }

    @GetMapping
    public String showAllAlbums(Model model) {
        model.addAttribute("albumsList", albumDAO.getAllAlbums());

        return "albums/showAllAlbums";
    }

    @GetMapping("/{id}")
    public String showCertainAlbum(@PathVariable("id") int id, Model model,
                                   @ModelAttribute("visitor") Visitor visitor) {
        model.addAttribute("album", albumDAO.getCertainAlbum(id));

        Optional<Visitor> albumOwner = albumDAO.getAlbumOwner(id);
        if (albumOwner.isPresent()) {
            model.addAttribute("owner", albumOwner.get());
        } else {
            model.addAttribute("visitorsList", visitorDAO.getAllVisitors());
        }
        return "albums/showCertainAlbum";
    }

    @GetMapping("/newAlbum")
    public String newAlbum(@ModelAttribute("album") Album album) {

        return "albums/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("album") @Valid Album album,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {

            return "albums/new";
        } else {
            albumDAO.saveAlbum(album);
        }

        return "redirect:/albums";
    }

    @GetMapping("/{id}/editAlbum")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("album", albumDAO.getCertainAlbum(id));

        return "albums/editCertainAlbum";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("album") @Valid Album album,
                         BindingResult bindingResult, @PathVariable("id") int id) {
        if (bindingResult.hasErrors()) {

            return "albums/editCertainAlbum";
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
    public String release(@PathVariable("id") int id) {
        albumDAO.releaseAlbum(id);

        return "redirect:/albums/" + id;
    }

    @PatchMapping("/{id}/assignAlbum")
    public String assign(@PathVariable("id") int id, @ModelAttribute("album") Visitor visitor) {
        albumDAO.assignAlbum(id, visitor);

        return "redirect:/albums/" + id;
    }
}
