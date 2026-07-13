package it.uniroma3.siw.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import it.uniroma3.siw.model.Partita;
import it.uniroma3.siw.repository.RepoPartita;
import java.util.List;

@Service
public class ServPartita {

    @Autowired
    private RepoPartita partitaRepository;

}
