package org.example.matcheweb;

import org.example.matcheweb.pojos.User;
import org.example.matcheweb.repositories.RepositoriesPremi;
import org.example.matcheweb.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
@Service
public class AssegnaPremi {
    private final UserRepository userRepository;
    private final RepositoriesPremi repositoriesPremi;


    public AssegnaPremi(UserRepository userRepository, RepositoriesPremi repositoriesPremi) {
        this.userRepository = userRepository;
        this.repositoriesPremi = repositoriesPremi;
    }

    public List <String> getPremi(){
        List <String> premi = new ArrayList<>();
        premi.add("maglia");
        premi.add("biglietto");
        premi.add("pizzeria");

        Collections.shuffle(premi);
        List<User> ClassificaUtenti = userRepository.getClassificaUtenti();
        repositoriesPremi.addPremi(ClassificaUtenti.get(0).getUsername(),premi.get(0));
        repositoriesPremi.addPremi(ClassificaUtenti.get(1).getUsername(),premi.get(1));
        repositoriesPremi.addPremi(ClassificaUtenti.get(2).getUsername(),premi.get(2));
        return premi;


    }

}
