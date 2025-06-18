package org.example.matcheweb.controllers;

import org.example.matcheweb.AssegnaPremi;
import org.example.matcheweb.pojos.User;
import org.example.matcheweb.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class AdminController {
    private final UserRepository userRepository;
    private final AssegnaPremi assegnaPremi;
    public AdminController(UserRepository userRepository, AssegnaPremi assegnaPremi) {
        this.userRepository = userRepository;
        this.assegnaPremi = assegnaPremi;
    }
    //controller per gestire le pagine dell'admin

    //per la lista utenti
    @GetMapping("/ListaUtenti")
    public String getUsers(Authentication authentication, Model model) {
        String name = authentication.getName();
        if (name != null) {
            model.addAttribute("name",name);
            model.addAttribute("users", userRepository.findAllUsers());
            return ("DashboardAdmin");}
        else
            return ("DashboardAdmin");
    }
    //per la classifica degli utanti
    @GetMapping("/ClassificaUtenti")
    public String ClassificaUtenti(Model model) {
        model.addAttribute("ListaUtenti", userRepository.getClassificaUtenti());
        return ("classificaUtenti");

    }
    //per assegnare i premi
    @GetMapping("/AssegnazionePremi01")
    public String AssegnazionePremi01(){
        return ("assegnazionePremi01");
    }
    @GetMapping("/AssegnazionePremi02")
    public String AssegnazionePremi02(Model model) {
        List<String> premi = assegnaPremi.getPremi();
        List<User> ClassificaUtenti = userRepository.getClassificaUtenti();

        model.addAttribute("Utente01", ClassificaUtenti.get(0).getUsername() );
        model.addAttribute("Utente02", ClassificaUtenti.get(1).getUsername() );
        model.addAttribute("Utente03", ClassificaUtenti.get(2).getUsername() );
        model.addAttribute("Premio01", premi.get(0) );
        model.addAttribute("Premio02", premi.get(1) );
        model.addAttribute("Premio03", premi.get(2) );
        return ("assegnazionePremi02");
    }
//    @GetMapping("/upgradeUser")
//    public String upgradeUser(Model model) {
//        model.addAttribute("userList", userRepository.getUsersOnly());
//        return "segments/adminActions/promote";
//    }

    // actual promotion of a user to admin
    // reloads the user list after promotion
//    @PostMapping("/promote")
//    public String promote(@RequestBody Map<String, Object> payload, Model model) {
//        int userId = (int) payload.get("userId");
//        userRepository.promoteUser(userId);
//        model.addAttribute("userList", userRepository.getUsersOnly());
//        return "segments/adminActions/promote";
//    }



}
