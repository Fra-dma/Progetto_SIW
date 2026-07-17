package it.uniroma3.siw.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import it.uniroma3.siw.model.Utente;
import it.uniroma3.siw.repository.RepoUtente;

@Service
public class ServUtente {

    @Autowired
    private RepoUtente repoUtente;

    @Transactional
    public Utente findByUsername(String username) {
        return repoUtente.findByUsername(username);
    }
    
    @Transactional
    public Utente salvaUtente(Utente utente) {
        return repoUtente.save(utente);
    }

}
