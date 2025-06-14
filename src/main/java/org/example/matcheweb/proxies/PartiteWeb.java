package org.example.matcheweb.proxies;


import org.example.matcheweb.configurations.OpenFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "PartiteWeb",
        url = "${name.service.url}",
        configuration = OpenFeignConfig.class)
public interface PartiteWeb {


}
