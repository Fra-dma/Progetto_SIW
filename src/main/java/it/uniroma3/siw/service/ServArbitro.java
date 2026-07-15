package it.uniroma3.siw.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import it.uniroma3.siw.model.Arbitro;
import it.uniroma3.siw.repository.RepoArbitro;
import java.util.List;

@Service
public class ServArbitro {

    @Autowired
    private RepoArbitro repoArbitro;
    
    @Transactional
    public List<Arbitro> findAll() {
        return (List<Arbitro>) repoArbitro.findAll();
    }
    
    @Transactional(readOnly = true)
    public Arbitro findById(Long id) {
        return repoArbitro.findById(id).orElse(null);
    }

}
