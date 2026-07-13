package it.uniroma3.siw.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import it.uniroma3.siw.model.Partita;

public interface RepoPartita extends CrudRepository<Partita, Long>{
	
	List<Partita> findByTorneoIdAndStato(Long idTorneo, String stato);
	
}
