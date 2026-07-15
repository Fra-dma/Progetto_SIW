package it.uniroma3.siw.repository;

import org.springframework.data.repository.CrudRepository;
import it.uniroma3.siw.model.Commento;
import it.uniroma3.siw.model.Partita;
import java.util.List;

public interface RepoCommento extends CrudRepository<Commento, Long> {
    List<Commento> findByPartita(Partita partita);
}
