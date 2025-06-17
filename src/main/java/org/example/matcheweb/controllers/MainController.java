package org.example.matcheweb.controllers;

import org.example.matcheweb.pojos.Recensione;
import org.example.matcheweb.pojos.User;
import org.example.matcheweb.proxies.PartiteWeb;
import org.example.matcheweb.repositories.UserRepository;
import org.example.matcheweb.repositories.recensioneRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.sql.Date;
import java.time.LocalDate;


@Controller
public class MainController {
    private final UserRepository userRepository;
    private final recensioneRepository recensioneRepository;
    private final PartiteWeb partiteWeb;

    MainController(UserRepository userRepository, recensioneRepository recensioneRepository, PartiteWeb partiteWeb) {
        this.userRepository = userRepository;
        this.recensioneRepository = recensioneRepository;
        this.partiteWeb = partiteWeb;
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

    //recensioni da recenssire
    @GetMapping("/recensioneuser")
    public String recensioni() {return "recensioniUser";}
    //recensioni da leggere
    @GetMapping("/recensioniglobale")
    public String recensioniGlobale() {return "RecensioniGlobale";}

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
//    @GetMapping("/ListaUtentiIscritti")
//    public String getUsers(Authentication authentication, Model model) {
//        String name = authentication.getName();
//        if (name != null) {
//            model.addAttribute("name",name);
//            model.addAttribute("users", userRepository.findAllUsers());
//            return ("ListaUtentiIscritti");}
//        else
//            return ("index");
//    }
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

    @GetMapping("/perform_logout")
    public String logoutPage(Model model) {
        model.addAttribute("logged", true);
        return "index";
    }

    @GetMapping("/logout")
    public String logout(Authentication authentication) { authentication.setAuthenticated(false);
        return "logout";
    }

//    @PostMapping ("/recensione")
//    public String addRecensione(@RequestParam int voto, @RequestParam String commento, Authentication authentication) {
//        String username = authentication.getName();
//        User user = userRepository.findByUsername(username);
//        if (user != null) {
//
//            recensioneRepository.addRecensione(user.getId(), voto, commento);
//        }
//        return "redirect:/recensioneuser";
//    }

    @PostMapping("/recensione")
    public String addRecensione(@RequestParam int voto, @RequestParam String commento, Authentication authentication) {
        String username = authentication.getName();
        User user = userRepository.findByUsername(username);
        if (user != null) {
            recensioneRepository.addRecensione(new Recensione(user.getId(),voto, commento));
        }
        return "redirect:/recensioneuser";
    }
    @GetMapping("/Listarecensioni")
    public String getRecensioni(Model model) {
        model.addAttribute("recensioni",recensioneRepository.findAllRecensioni());
        return ("RecensioniGlobale");
    }

    @PostMapping("/cambioPassword")
    public String cambioPassword(Authentication authentication,@RequestParam String passwordV,@RequestParam String passwordN,
                                 Model model) {
        boolean result = userRepository.cambiaPassword(authentication.getName(), passwordN, passwordV);
        model.addAttribute("passwordChanged", result);
        model.addAttribute("wrongPassword", !result);
        return "CambioPassword";
    }

    @GetMapping("/pagcambiopw")
    public String cambioPassword() {
        return ("CambioPassword");
    }

    @GetMapping("/calendario")
    public String calendario(Authentication authentication, Model model) {
        String sport = userRepository.FindSport(authentication.getName());
        model.addAttribute("partite",partiteWeb.getMatches(sport, LocalDate.now().toString()));
        return ("VisualizzaCalendario");
    }


}