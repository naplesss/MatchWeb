package org.example.matcheweb.proxies;


import org.example.matcheweb.configurations.OpenFeignConfig;
import org.example.matcheweb.pojos.Partita;
import org.example.matcheweb.pojos.Squadra;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "PartiteWeb",
        url = "${name.service.url}",
        configuration = OpenFeignConfig.class)
public interface PartiteWeb {
    @PostMapping("/listaPartite")
    public List<Partita> getMatches(@RequestParam String sport, @RequestParam String giorno);
    @GetMapping("/listaSquadre")
    public List<Squadra> getSquadreBySport(@RequestParam String sport);

    @PostMapping("/CalendarioPartite")
    public  List<Partita> getAllMatches(@RequestParam String sport);

    @PostMapping("/getTeams")
    public  List<Squadra> getTeams(@RequestParam String sport);

    // @PostMapping("/createMatches")
    // public  void createMatches(@RequestBody String sport);

    @PostMapping("/prendiRisultati")
    List<Integer> getResults(
            @RequestParam("sport") String sport,
            @RequestParam("giorno") String giorno,
            @RequestBody List<Integer> predictions);




}