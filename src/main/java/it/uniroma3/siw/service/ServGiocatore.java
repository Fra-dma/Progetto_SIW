package it.uniroma3.siw.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import it.uniroma3.siw.model.Giocatore;
import it.uniroma3.siw.model.Squadra;
import it.uniroma3.siw.repository.RepoGiocatore;
import java.util.List;

@Service
public class ServGiocatore {

    @Autowired
    private RepoGiocatore giocatoreRepository;

    @Transactional(readOnly = true)
    public List<Giocatore> findAllGiocatori() {
        return (List<Giocatore>) giocatoreRepository.findAll();
    }
    
    @Transactional(readOnly = true)
    public List<Giocatore> findBySquadra(Squadra squadra) {
        return giocatoreRepository.findBySquadra(squadra);
    }
}
