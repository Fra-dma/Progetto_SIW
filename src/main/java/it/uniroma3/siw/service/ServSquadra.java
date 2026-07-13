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
    private RepoSquadra squadraRepository;
    
    @Transactional(readOnly = true)
    public List<Squadra> findAllSquadre() {
        return (List<Squadra>) squadraRepository.findAll();
    }
    
    @Transactional(readOnly = true)
    public Squadra findById(Long id) {
        return squadraRepository.findById(id).orElse(null);
    }

}
