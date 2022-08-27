package project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.dao.VisitorDAO;
import project.entity.Visitor;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final VisitorDAO visitorDAO;

    @Autowired
    public AdminController(VisitorDAO visitorDAO) {
        this.visitorDAO = visitorDAO;
    }

    @GetMapping
    public String adminPage(Model model, @ModelAttribute("artist") Visitor visitor) {
        model.addAttribute("artists", visitorDAO.getAllVisitors());

        return "adminPage";
    }

    @PatchMapping("/add")
    public String makeAdmin(@ModelAttribute("artist") Visitor visitor) {
        System.out.println(visitor.getId());

        return "redirect:/artists";
    }
}
