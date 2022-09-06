package project.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import project.entity.Visitor;
import project.service.VisitorService;

import javax.validation.Valid;

@Controller
@RequestMapping("/visitors")
public class VisitorController {
    private final VisitorService visitorService;

    @Autowired
    public VisitorController(VisitorService visitorService) {
        this.visitorService = visitorService;
    }

    @GetMapping
    public String showAllVisitors(Model model) {
        model.addAttribute("visitorsList", visitorService.findAllVisitors());
        return "visitors/showAllVisitors";
    }

    @GetMapping("/{id}")
    public String showVisitorById(@PathVariable("id") int id, Model model) {
        model.addAttribute("visitor", visitorService.findVisitorById(id));
        return "visitors/showCertainVisitor";
    }

    @GetMapping("/newVisitor")
    public String newVisitor(@ModelAttribute("visitor") Visitor visitor) {
        return "visitors/new";
    }

    @PostMapping
    public String create(@ModelAttribute("visitor") @Valid Visitor visitor, BindingResult bindingResult) {

        if (bindingResult.hasErrors())
            return "visitors/new";

        visitorService.save(visitor);
        return "redirect:/visitors";
    }

    @GetMapping("/{id}/editVisitor")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("visitor", visitorService.findVisitorById(id));
        return "visitors/editCertainVisitor";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("visitor") @Valid Visitor visitor, BindingResult bindingResult,
                         @PathVariable("id") int id) {

        if (bindingResult.hasErrors())
            return "visitors/editCertainVisitor";

        visitorService.update(id, visitor);
        return "redirect:/visitors";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        visitorService.delete(id);
        return "redirect:/visitors";
    }
}
