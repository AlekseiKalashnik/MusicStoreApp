package project.controllers;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import project.dao.VisitorDAO;
import project.entity.Visitor;
import project.util.VisitorValidator;

@Controller
@RequestMapping("/visitors")
public class VisitorController {

    private final VisitorDAO visitorDAO;
    private final VisitorValidator visitorValidator;

    @Autowired
    public VisitorController(VisitorDAO visitorDAO, VisitorValidator visitorValidator) {
        this.visitorDAO = visitorDAO;
        this.visitorValidator = visitorValidator;
    }

    @GetMapping
    public String showAllVisitors(Model model) {
        model.addAttribute("visitorsList", visitorDAO.getAllVisitors());

        return "visitors/showAllVisitors";
    }

    @GetMapping("/{id}")
    public String showVisitorById(@PathVariable("id") int id, Model model) {
        model.addAttribute("visitor", visitorDAO.getVisitorById(id));
        model.addAttribute("albumsList", visitorDAO.getAlbumsByVisitorId(id));

        return "visitors/showCertainVisitor";
    }

    @GetMapping("/newVisitor")
    public String newVisitor(@ModelAttribute("visitor") Visitor visitor) {

        return "visitors/new";
    }

    @PostMapping
    public String create(@ModelAttribute("visitor") @Valid Visitor visitor,
                         BindingResult bindingResult) {
        visitorValidator.validate(visitor, bindingResult);

        if (bindingResult.hasErrors())
            return "visitors/new";

        visitorDAO.save(visitor);
        return "redirect:/visitors";
    }

    @GetMapping("/{id}/editVisitor")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("visitor", visitorDAO.getVisitorById(id));

        return "visitors/editCertainVisitor";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("visitor") @Valid Visitor visitor,
                         BindingResult bindingResult, @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "visitors/editCertainVisitor";

        visitorDAO.update(id, visitor);
        return "redirect:/visitors";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        visitorDAO.delete(id);

        return "redirect:/visitors";
    }
}
