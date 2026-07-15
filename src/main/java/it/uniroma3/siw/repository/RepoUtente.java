package it.uniroma3.siw.repository;

import org.springframework.data.repository.CrudRepository;
import it.uniroma3.siw.model.Utente;

public interface RepoUtente extends CrudRepository<Utente, Long>{
	
	public Utente findByUsername(String username);
	
}
