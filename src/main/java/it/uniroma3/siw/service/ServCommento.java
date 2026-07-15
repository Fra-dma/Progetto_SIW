package it.uniroma3.siw.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import it.uniroma3.siw.model.Commento;
import it.uniroma3.siw.model.Partita;
import it.uniroma3.siw.repository.RepoCommento;
import java.util.List;

@Service
public class ServCommento {

    @Autowired
    private RepoCommento repoCommento;

    @Transactional
    public void save(Commento commento) {
        repoCommento.save(commento);
    }

    @Transactional(readOnly = true)
    public Commento findById(Long id) {
        return repoCommento.findById(id).orElse(null);
    }

    @Transactional(readOnly = true)
    public List<Commento> findByPartita(Partita partita) {
        return repoCommento.findByPartita(partita);
    }

    @Transactional
    public void delete(Commento commento) {
        repoCommento.delete(commento);
    }

    @Transactional
    public Commento salvaCommento(Commento commento) {
        return repoCommento.save(commento);
    }
}
