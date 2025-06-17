package org.example.matcheweb.proxies;


import org.example.matcheweb.configurations.OpenFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "PartiteWeb",
        url = "${name.service.url}",
        configuration = OpenFeignConfig.class)
public interface PartiteWeb<Squadra> {

    @GetMapping("/listaSquadre")
    public ResponseEntity<List<Squadra>> getSquadreBySport(@RequestParam String sport);
}