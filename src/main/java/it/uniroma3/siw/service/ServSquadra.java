package it.uniroma3.siw.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import it.uniroma3.siw.model.Squadra;
import it.uniroma3.siw.repository.RepoSquadra;
import java.util.List;

@Service
public class ServSquadra {

    @Autowired
    private RepoSquadra repoSquadra;
    
    @Transactional(readOnly = true)
    public List<Squadra> findAllSquadre() {
        return (List<Squadra>) repoSquadra.findAll();
    }
    
    @Transactional(readOnly = true)
    public Squadra findById(Long id) {
        return repoSquadra.findById(id).orElse(null);
    }
    
    @Transactional
    public Squadra salvaSquadra(Squadra squadra) {
        return repoSquadra.save(squadra);
    }

}
