package org.example.matcheweb.configurations;


import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("org.example.matcheweb")
@EnableFeignClients(basePackages = "org.example.matcheweb")
public class OpenFeignConfig {
}
