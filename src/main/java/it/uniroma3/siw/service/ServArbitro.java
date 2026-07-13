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
    private RepoArbitro arbitroRepository;

}
