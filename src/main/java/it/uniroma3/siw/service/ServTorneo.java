package it.uniroma3.siw.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import it.uniroma3.siw.model.Torneo;
import it.uniroma3.siw.model.Squadra;
import it.uniroma3.siw.model.Partita;
import it.uniroma3.siw.repository.RepoPartita;
import it.uniroma3.siw.repository.RepoTorneo;
import jakarta.persistence.EntityManager;
import it.uniroma3.siw.model.ClassificaSquadra;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ServTorneo {

    @Autowired
    private RepoTorneo repoTorneo;

    @Autowired
    private RepoPartita repoPartita;
    
    @Autowired
    private EntityManager entityManager;

    @Transactional(readOnly = true)
    public List<Torneo> findAllTornei() {
        return (List<Torneo>) repoTorneo.findAll();
    }
    
    public List<Partita> findAllPartite(Long idTorneo) {
        Torneo torneo = repoTorneo.findById(idTorneo).orElse(null);
        
        if (torneo == null) {
            return new ArrayList<>(); 
        }
        
        return torneo.getPartite();
    }
    
    @Transactional
    public Torneo salvaTorneo(Torneo torneo) {
        return repoTorneo.save(torneo);
    }
    
    @Transactional(readOnly = true)
    public Torneo findTorneoConPartite(Long id) {
        return repoTorneo.findByIdWithPartiteEager(id).orElse(null);
    }
    
    @Transactional(readOnly = true)
    public Torneo findById(Long id) {
        return repoTorneo.findById(id).orElse(null);
    }

    @Transactional(readOnly = true)
    public List<ClassificaSquadra> calcolaClassifica(Long idTorneo) {
    	Torneo torneo = repoTorneo.findByIdWithSquadre(idTorneo).orElse(null);
        List<ClassificaSquadra> classificaFinale = new ArrayList<>();

        if (torneo == null) {
            return classificaFinale;
        }

        // Creazione mappa tutti a 0 punti
        Map<Long, ClassificaSquadra> mappaClassifica = new HashMap<>();
        for (Squadra squadra : torneo.getSquadrePartecipanti()) {
            mappaClassifica.put(squadra.getId(), new ClassificaSquadra(squadra));
        }

        // Passate dal DB solo le partite giocate
        List<Partita> partiteGiocate = repoPartita.findByTorneoIdAndStato(idTorneo, "PLAYED");

        // Calcolo del punteggio
        for (Partita partita : partiteGiocate) {
            
            Squadra casa = partita.getSquadraCasa();
            Squadra ospite = partita.getSquadraOspite();
            
            // Controllo di sicurezza
            if (casa != null && ospite != null) {
                
                ClassificaSquadra classCasa = mappaClassifica.get(casa.getId());
                ClassificaSquadra classOspite = mappaClassifica.get(ospite.getId());
                
                if (classCasa != null && classOspite != null) {
                    int golCasa = partita.getGoalsHome();
                    int golOspite = partita.getGoalsAway();

                    if (golCasa > golOspite) {
                        classCasa.aggiungiPunti(3);
                    } else if (golCasa < golOspite) {
                        classOspite.aggiungiPunti(3);
                    } else {
                        classCasa.aggiungiPunti(1);
                        classOspite.aggiungiPunti(1);
                    }
                }
            }
        }

        // Ordinazione
        classificaFinale.addAll(mappaClassifica.values());
        Collections.sort(classificaFinale);

        return classificaFinale;
    }
    
     /**
     * ESPERIMENTO PER L'ANALISI DELLE PRESTAZIONI
     */
     @Transactional(readOnly = true)
     public void eseguiAnalisiSperimentale(Long idTorneo) {
        System.out.println("=== INIZIO ANALISI SPERIMENTALE ===");

        // APPROCCIO LAZY
        long startLazy = System.nanoTime();
        
        Torneo torneoLazy = this.repoTorneo.findById(idTorneo).orElse(null);
        if (torneoLazy != null) {
            int numeroPartite = torneoLazy.getPartite().size(); 
            System.out.println("Trovate " + numeroPartite + " partite con approccio LAZY");
        }
        
        long endLazy = System.nanoTime();
        long tempoLazy = endLazy - startLazy;

        // Pulisco la cache di Hibernate prima del prossimo test
        entityManager.clear();


        // APPROCCIO JOIN FETCH
        long startEager = System.nanoTime();
        
        Torneo torneoEager = this.repoTorneo.findByIdWithPartiteEager(idTorneo).orElse(null);
        if (torneoEager != null) {
            int numeroPartite = torneoEager.getPartite().size();
            System.out.println("Trovate " + numeroPartite + " partite con approccio JOIN FETCH");
        }
        
        long endEager = System.nanoTime();
        long tempoEager = endEager - startEager;

        // Pulisco la cache di Hibernate prima del prossimo test
        entityManager.clear();


        // APPROCCIO ENTITY GRAPH
        long startGraph = System.nanoTime();
        
        Torneo torneoGraph = this.repoTorneo.findTorneoById(idTorneo).orElse(null);
        if (torneoGraph != null) {
            int numeroPartite = torneoGraph.getPartite().size();
            System.out.println("Trovate " + numeroPartite + " partite con approccio ENTITY GRAPH");
        }
        
        long endGraph = System.nanoTime();
        long tempoGraph = endGraph - startGraph;


        // STAMPA DEI RISULTATI
        System.out.println("===================================");
        System.out.println("Tempo LAZY (N+1 query): " + (tempoLazy / 1_000_000.0) + " ms");
        System.out.println("Tempo JOIN FETCH: " + (tempoEager / 1_000_000.0) + " ms");
        System.out.println("Tempo ENTITY GRAPH: " + (tempoGraph / 1_000_000.0) + " ms");
        System.out.println("===================================");
    }
}