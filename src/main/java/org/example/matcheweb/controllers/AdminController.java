package org.example.matcheweb.controllers;

import org.example.matcheweb.pojos.User;
import org.example.matcheweb.repositories.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AdminController {
    private final UserRepository userRepository;
    public AdminController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @GetMapping("/ListaUtenti")
    public String getUsers(Authentication authentication, Model model) {
        String name = authentication.getName();
        if (name != null) {
            model.addAttribute("name",name);
            model.addAttribute("users", userRepository.findAllUsers());
            return ("DashboardAdmin");}
        else
            return ("index");
    }
    /*@GetMapping("/ListaUtenti")
    public String ListaUtenti(Model model){
        model.addAttribute("users", userRepository.findAllUsers());
        return "ListaUtentiIscritti";
    }*/
    @GetMapping("/ClassificaUtenti")
    public String ClassificaUtenti(Model model){
        model.addAttribute("ListaUtenti", userRepository.getClassificaUtenti());
        return "segments/adminActions/ListaUtenti";
    }
}
