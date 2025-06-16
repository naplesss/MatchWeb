package org.example.matcheweb.controllers;

import org.example.matcheweb.pojos.User;
import org.example.matcheweb.repositories.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


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
                          @RequestParam String birthdate,
                          @RequestParam String email,
                          @RequestParam String username,
                          @RequestParam String password,
                          @RequestParam String sport,
                          @RequestParam String sportcuore,
                          Model model) {
        String returnPage;
        //cambiare signup failure
        if (userRepository.userExists(username)) {
            System.out.println("User already exists");
            model.addAttribute("username", username);
            returnPage = "signupFailure";
        } else {
            userRepository.addUser(new User(firstName, lastName, email, username, password, "ROLE_USER", birthdate, sport, sportcuore));
            model.addAttribute("username", username);
            returnPage = "tuttapposto";
        }
        return returnPage;
    }

    @PostMapping("/loginFailure")
    public String loginFallito(Model model) {
        model.addAttribute("logged", false);
        model.addAttribute("loginFailed", true);
        return "/login";
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



}