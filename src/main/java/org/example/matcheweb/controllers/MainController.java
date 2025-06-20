package org.example.matcheweb.controllers;

import org.example.matcheweb.pojos.Partita;
import org.example.matcheweb.pojos.Recensione;
import org.example.matcheweb.pojos.Squadra;
import org.example.matcheweb.pojos.User;
import org.example.matcheweb.proxies.PartiteWeb;
import org.example.matcheweb.repositories.UserRepository;
import org.example.matcheweb.repositories.recensioneRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


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

    //tiro alla fune
    @GetMapping("/fune")
    public String fune() {return "fune";}

    //lacrosse
    @GetMapping("/lacrosse")
    public String lacrosse() {return "lacrosse";}

    //pagina sponsor
    @GetMapping("/sponsor")
    public String sponsor() {return "sponsor";}

    //recensioni da recenssire
    @GetMapping("/recensioneuser")
    public String recensioni() {return "recensioniUser";}
    //recensioni da leggere
    @GetMapping("/recensioniglobale")
    public String recensioniGlobale() {return "RecensioniGlobale";}

    //per registraree un nuovo utente
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
    //errori di login
    @PostMapping("/loginFailure")
    public String loginFallito(Model model) {

            model.addAttribute("erroreNome", "username o password non corretti");

        return "login";
    }

    //pagina news
    @GetMapping("/News")
    public String getNews(){
        return ("News");
    }

    //dashboard user
    @GetMapping("/dashboardUser")
    public String userDashboard(Authentication authentication, Model model) {
        model.addAttribute("name", authentication.getName());
        return "DashboardUser";
    }

    //dashboard admin
    @GetMapping("/dashboardAdmin")
    public String adminDashboard(Authentication authentication, Model model) {
        model.addAttribute("name", authentication.getName());
        return "DashboardAdmin";
    }

    //dashboard pe indirizzare in base al ruolo
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

    //per il logout
    @GetMapping("/perform_logout")
    public String logoutPage(Model model) {
        model.addAttribute("logged", true);
        return "index";
    }

    //per la pagina di logout
    @GetMapping("/logout")
    public String logout(Authentication authentication) { authentication.setAuthenticated(false);
        return "logout";
    }

    //per scrivere una recensione
    @PostMapping("/recensione")
    public String addRecensione(@RequestParam int voto, @RequestParam String commento, Authentication authentication) {
        String username = authentication.getName();
        User user = userRepository.findByUsername(username);
        if (user != null) {
            recensioneRepository.addRecensione(new Recensione(user.getId(),voto, commento));
        }
        return "redirect:/recensioneuser";
    }

    //lista delle recensioni
    @GetMapping("/Listarecensioni")
    public String getRecensioni(Model model) {
        model.addAttribute("recensioni",recensioneRepository.findAllRecensioni());
        return ("RecensioniGlobale");
    }

    //cambiare la password
    @PostMapping("/cambioPassword")
    public String cambioPassword(Authentication authentication,@RequestParam String passwordV,@RequestParam String passwordN,
                                 Model model) {
        boolean result = userRepository.cambiaPassword(authentication.getName(), passwordN, passwordV);
        model.addAttribute("passwordChanged", result);
        model.addAttribute("wrongPassword", !result);
        return "CambioPassword";
    }

    //pagina cambio password
    @GetMapping("/pagcambiopw")
    public String cambioPassword() {
        return ("CambioPassword");
    }

    //pagina calendario
    @GetMapping("/calendario")
    public String calendario(Authentication authentication, Model model) {
        String sport = userRepository.FindSport(authentication.getName());
        model.addAttribute("partite",partiteWeb.getMatches(sport, LocalDate.now().toString()));
        return ("VisualizzaCalendario");
    }

    //restituisce i team
    @PostMapping("/getTeams")
    @ResponseBody
    public List<Squadra> post(@RequestBody Map<String, String> data) {
        String sport = data.get("sport");
        return partiteWeb.getTeams(sport);
    }

    //pagina per giocare la schedina
    @GetMapping("/schedina")
    public String schedina(Model model) {
        List<Partita> partite = partiteWeb.getMatches("lacrosse", LocalDate.now().toString());

        //per gestire i null
        Partita defaultPartita = new Partita();
        defaultPartita.setId_squadracasa("N/D");
        defaultPartita.setId_squadrafuori("N/D");

        // Usa le partite reali se esistono, altrimenti quelle di default
        model.addAttribute("partita1", partite != null && !partite.isEmpty() ? partite.get(0) : defaultPartita);
        model.addAttribute("partita2", partite != null && partite.size() > 1 ? partite.get(1) : defaultPartita);

        return("Schedina");}

    //risultati delle scommesse
    @PostMapping("/scommesse")
    public ResponseEntity<List<Integer>> post(@RequestParam int pronostico1, @RequestParam int pronostico2, Model model) {
        List<Integer> pronostici = new ArrayList<>();
        pronostici.add(pronostico1);
        pronostici.add(pronostico2);
        List<Integer> risultati = partiteWeb.getResults("lacrosse", LocalDate.now().toString(),pronostici);
        model.addAttribute("risultati", risultati);
        return ResponseEntity.ok(risultati);
    }





}