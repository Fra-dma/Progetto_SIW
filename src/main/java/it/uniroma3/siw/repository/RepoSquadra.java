package it.uniroma3.siw.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import it.uniroma3.siw.model.Squadra;

public interface RepoSquadra extends CrudRepository<Squadra, Long>{
	
	@Query("SELECT s FROM Squadra s LEFT JOIN FETCH s.giocatori WHERE s.id = :id")
    Squadra findByIdWithGiocatori(@Param("id") Long id);
	
}
