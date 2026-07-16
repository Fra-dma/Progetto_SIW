package it.uniroma3.siw.repository;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;
import it.uniroma3.siw.model.Partita;

public interface RepoPartita extends CrudRepository<Partita, Long>{
	
	@EntityGraph(attributePaths = {"squadraCasa", "squadraOspite"})
    List<Partita> findByTorneoIdAndStato(Long idTorneo, String stato);
	
}
