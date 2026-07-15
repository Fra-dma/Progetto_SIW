package it.uniroma3.siw.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
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
            		.requestMatchers(HttpMethod.POST, "/partita/*/commento").authenticated()
            	    
            	    // 2. REGOLE PUBBLICHE "CORAZZATE"
            	    // NOVITÀ: Ho aggiunto i plurali (/tornei/**, /squadre/**), le classifiche e /error
            	    .requestMatchers("/", "/tornei", "/tornei/**", "/torneo/**", "/classifiche/**", "/classifica/**", "/squadre", "/squadre/**", "/squadra/**", "/partita/**", "/error", "/css/**", "/images/**", "/login").permitAll()
            	    
            	    // 3. REGOLE ADMIN (Rotte riservate alla tua dashboard e alla creazione di elementi)
            	    .requestMatchers("/admin/**").hasRole("ADMIN")
            	    
            	    // 4. IL RACCOGLITUTTO (Tutto ciò che non è in lista sopra viene bloccato)
            	    .anyRequest().authenticated()
            )
            .formLogin((form) -> form
                
                .loginPage("/login")
                .permitAll()
                .defaultSuccessUrl("/tornei", true)
            )
            .logout((logout) -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/tornei")
                .permitAll()
            );

        return http.build();
    }
}
