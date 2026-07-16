package it.uniroma3.siw.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import it.uniroma3.siw.model.Utente;
import it.uniroma3.siw.repository.RepoUtente;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private RepoUtente repoUtente;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if (repoUtente.findByUsername("admin") == null) {
            Utente admin = new Utente();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("password"));
            admin.setRuolo("ADMIN");
            repoUtente.save(admin);
        }
        Utente user = new Utente();
        user.setUsername("user");
        user.setPassword(passwordEncoder.encode("password"));
        user.setRuolo("USER");
        repoUtente.save(user);
    }
}