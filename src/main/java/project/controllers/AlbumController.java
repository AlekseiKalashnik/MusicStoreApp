package project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import project.entity.Album;
import project.service.AlbumService;

import javax.validation.Valid;

@Controller
@RequestMapping("/albums")
public class AlbumController {
    private final AlbumService albumService;

    @Autowired
    public AlbumController(AlbumService albumService) {
        this.albumService = albumService;
    }

    @GetMapping
    public String showAllAlbums(Model model) {
        model.addAttribute("albumsList", albumService.findAllAlbums());
        return "albums/showAllAlbums";
    }

    @GetMapping("/{id}")
    public String showAlbumById(@PathVariable("id") int id, Model model) {
        model.addAttribute("album", albumService.findAlbumById(id));
        return "albums/showCertainAlbum";
    }

    @GetMapping("/newAlbum")
    public String newAlbum(@ModelAttribute("album") Album album) {
        return "albums/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("album") @Valid Album album,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "albums/new";

        albumService.save(album);
        return "redirect:/albums";
    }

    @GetMapping("/{id}/editAlbum")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("album", albumService.findAlbumById(id));
        return "albums/editCertainAlbum";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("album") @Valid Album album,
                         BindingResult bindingResult, @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "albums/editCertainAlbum";

        albumService.update(id, album);
        return "redirect:/albums";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        albumService.delete(id);
        return "redirect:/albums";
    }
}
