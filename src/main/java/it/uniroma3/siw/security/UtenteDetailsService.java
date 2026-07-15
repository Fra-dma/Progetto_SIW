package it.uniroma3.siw.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Utente;
import it.uniroma3.siw.repository.RepoUtente;

@Service
public class UtenteDetailsService implements UserDetailsService {

    @Autowired
    private RepoUtente utenteRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Utente utente = utenteRepository.findByUsername(username);
        
        if (utente == null) {
            throw new UsernameNotFoundException("Utente non trovato");
        }
        
        return User.builder()
            .username(utente.getUsername())
            .password(utente.getPassword())
            .roles(utente.getRuolo())
            .build();
    }
}
