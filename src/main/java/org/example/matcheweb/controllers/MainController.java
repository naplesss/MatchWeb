package org.example.matcheweb.controllers;

import org.example.matcheweb.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


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
        return "correct";
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




}