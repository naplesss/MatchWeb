package org.example.matcheweb.controllers;

import org.example.matcheweb.pojos.User;
import org.example.matcheweb.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.sql.Date;
import java.util.List;


@Controller
public class MainController {
    private final UserRepository userRepository;

    MainController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //home page
    @GetMapping("/index")
    public String index() {
        return "index";
    }

    //login
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    // Signing up
    @GetMapping("/signup")
    public String signup() {
        return "signup";
    }

    // registrazione andata bene
    @GetMapping("/correct")
    public String correct() {
        return "tuttapposto";
    }

    // logout
    @GetMapping("/logout")
    public String logout() {
        return "logout";
    }

    // polo
    @GetMapping("/polo")
    public String polo() {
        return "polo";
    }

    //fune
    @GetMapping("/fune")
    public String fune() {return "fune";}

    //lacrosse
    @GetMapping("/lacrosse")
    public String lacrosse() {return "lacrosse";}

    //sponsor
    @GetMapping("/sponsor")
    public String sponsor() {return "sponsor";}

    //recensioni
    @GetMapping("/recensioni")
    public String recensioni() {return "recensioni";}

    @PostMapping("/addUser")
    public String addUser(@RequestParam String firstName,
                          @RequestParam String lastName,
                          @RequestParam Date birthdate,
                          @RequestParam String email,
                          @RequestParam String username,
                          @RequestParam String password,
                          @RequestParam String sport,
                          @RequestParam String sportcuore,
                          Model model) {
        String returnPage;
        // signup failure
        if (userRepository.userExists(username)) {
            model.addAttribute("username", username);
            model.addAttribute("erroreNome", "Username già esistente! Scegli un altro nome utente.");
            returnPage = "signup";
        } else if (userRepository.isMaggiorenne(birthdate)) {
            model.addAttribute("erroreData", "l'utente deve essere maggiorenne");
            returnPage = "signup";
            
        } else {
            userRepository.addUser(new User(firstName, lastName, email, username, password, "ROLE_USER", birthdate, sport, sportcuore));
            model.addAttribute("username", username);
            returnPage = "tuttapposto";
        }
        return returnPage;
    }

    @PostMapping("/loginFailure")
    public String loginFallito(Model model) {

            model.addAttribute("erroreNome", "username o password non corretti");

        return "login";
    }
//roba di gaia per admin dashbpard
    @GetMapping("/ListaUtentiIscritti")
    public String getUsers(Authentication authentication, Model model) {
        String name = authentication.getName();
        if (name != null) {
            model.addAttribute("name",name);
            model.addAttribute("users", userRepository.findAllUsers());
            return ("ListaUtentiIscritti");}
        else
            return ("index");
    }
    @GetMapping("/News")
    public String getNews(){
        return ("News");
    }

    @GetMapping("/dashboardUser")
    public String userDashboard(Authentication authentication, Model model) {
        model.addAttribute("name", authentication.getName());
        return "DashboardUser";
    }

    @GetMapping("/dashboardAdmin")
    public String adminDashboard(Authentication authentication, Model model) {
        model.addAttribute("name", authentication.getName());
        return "DashboardAdmin";
    }

    @GetMapping("/dashboard")
    public String dashboard(Authentication authentication) {
        String returnPage;
        if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
            returnPage = "forward:dashboardAdmin";
        } else {
            returnPage = "forward:dashboardUser";
        }
        return returnPage;
    }



}