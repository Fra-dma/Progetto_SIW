package it.uniroma3.siw.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import it.uniroma3.siw.model.Torneo;
import it.uniroma3.siw.model.Squadra;
import it.uniroma3.siw.model.Partita;
import it.uniroma3.siw.repository.RepoPartita;
import it.uniroma3.siw.repository.RepoTorneo;
import it.uniroma3.siw.model.ClassificaSquadra;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ServTorneo {

    @Autowired
    private RepoTorneo torneoRepository;

    @Autowired
    private RepoPartita partitaRepository;

    @Transactional(readOnly = true)
    public List<Torneo> findAllTornei() {
        return (List<Torneo>) torneoRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<ClassificaSquadra> calcolaClassifica(Long idTorneo) {
        Torneo torneo = torneoRepository.findById(idTorneo).orElse(null);
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
        List<Partita> partiteGiocate = partitaRepository.findByTorneoIdAndStato(idTorneo, "PLAYED");

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
}