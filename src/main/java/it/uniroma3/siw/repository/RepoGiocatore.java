package it.uniroma3.siw.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import it.uniroma3.siw.model.Giocatore;
import it.uniroma3.siw.model.Squadra;

public interface RepoGiocatore extends CrudRepository<Giocatore, Long>{
	
	List<Giocatore> findBySquadra(Squadra squadra);
	
}
