package it.uniroma3.siw.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import it.uniroma3.siw.model.Partita;
import it.uniroma3.siw.repository.RepoPartita;

@Service
public class ServPartita {

    @Autowired
    private RepoPartita repoPartita;
    
    @Transactional(readOnly = true)
    public Partita findById(Long id) {
        return repoPartita.findById(id).orElse(null);
    }
    
    @Transactional
    public Partita salvaPartita(Partita p) {
        return repoPartita.save(p);
    }

}
