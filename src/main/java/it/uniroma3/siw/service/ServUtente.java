package it.uniroma3.siw.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import it.uniroma3.siw.model.Utente;
import it.uniroma3.siw.repository.RepoUtente;
import java.util.List;

@Service
public class ServUtente {

    @Autowired
    private RepoUtente utenteRepository;

}
