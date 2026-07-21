package it.uniroma3.siw.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import it.uniroma3.siw.model.Torneo;

public interface RepoTorneo extends CrudRepository<Torneo, Long>{
	
	@Query("SELECT t FROM Torneo t LEFT JOIN FETCH t.squadrePartecipanti WHERE t.id = :id")
	Optional<Torneo> findByIdWithSquadre(@Param("id") Long id);
	
	// METODOI DI TEST PER ANALISI SPERIMENTALE
	//Lazy
	Optional<Torneo> findById(Long id);
	
	// Metodo ottimizzato con Join Fetch
	@Query("SELECT t FROM Torneo t LEFT JOIN FETCH t.partite WHERE t.id = :id")
    Optional<Torneo> findByIdWithPartiteEager(@Param("id") Long id);
	
	// Metodo ottimizzato con Entity Graph
	@EntityGraph(attributePaths = {"partite"})
    Optional<Torneo> findTorneoById(Long id);
	
}
