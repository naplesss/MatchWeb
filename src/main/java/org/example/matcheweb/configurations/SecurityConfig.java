package org.example.matcheweb.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@ComponentScan("org.example.matcheweb")
public class SecurityConfig {


    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {
        return new JdbcUserDetailsManager(dataSource);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public SecurityFilterChain configure(HttpSecurity http)
            throws Exception {


        http.formLogin(c ->
                c.loginPage("/login")
                        .defaultSuccessUrl("/dashboard")
                        .failureForwardUrl("/loginFailure")
        );


        http.authorizeHttpRequests(c ->
                c.requestMatchers("/dashboard").hasAnyRole("ADMIN", "USER", "MODERATOR")
                        .requestMatchers("/dashboardUser").hasAnyRole( "USER", "MODERATOR")
                        .requestMatchers("/recensioneuser").hasAnyRole("USER","MODERATOR")
                        .requestMatchers("/recensione").hasAnyRole("USER","MODERATOR")
                        .requestMatchers("/cambioPassword").hasAnyRole( "USER","MODERATOR")
                        .requestMatchers("/calendario").hasAnyRole("USER","MODERATOR")
                        .requestMatchers("/ProfiloUtente").hasAnyRole("USER","MODERATOR")
                        .requestMatchers("/dashboardAdmin").hasRole("ADMIN")
                        .requestMatchers("/ListaUtenti").hasRole("ADMIN")
                        .requestMatchers("/ClassificaUtenti").hasRole("ADMIN")
                        .anyRequest().permitAll()
        );


        http.logout(c ->
                c.logoutUrl("/perform_logout")
                        .logoutSuccessUrl("/index")
        );


        http.csrf(AbstractHttpConfigurer::disable);

        return http.build();
    }
}
