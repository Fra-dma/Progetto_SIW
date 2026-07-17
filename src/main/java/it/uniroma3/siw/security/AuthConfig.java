package it.uniroma3.siw.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class AuthConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests((requests) -> requests
                
                // PUBBLICHE
                .requestMatchers("/", "/tornei", "/tornei/**", "/torneo/**", "/classifiche/**", "/classifica/**", 
                                 "/squadre", "/squadre/**", "/squadra/**", "/partita/**", "/error", 
                                 "/css/**", "/images/**", "/login", "/ricerca_squadre", "/api/**" ).permitAll()
                
                // ESCLUSIVE PER L'AMMINISTRATOR
                .requestMatchers("/admin/**").hasRole("ADMIN")
                
                // PER UTENTI LOGGATI (USER e ADMIN)
                .anyRequest().authenticated()
            )
            
            // LOGIN
            .formLogin((form) -> form
                .loginPage("/login")
                .permitAll()
                .defaultSuccessUrl("/tornei", true)
            )
            
            // OAuth
            .oauth2Login((oauth2) -> oauth2
                .loginPage("/login")
                .defaultSuccessUrl("/tornei", true)
            )
            
            // LOGOUT
            .logout((logout) -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/tornei")
                .permitAll()
            );

        return http.build();
    }
}
