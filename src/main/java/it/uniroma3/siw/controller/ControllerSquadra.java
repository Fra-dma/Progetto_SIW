package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import it.uniroma3.siw.model.Giocatore;
import it.uniroma3.siw.model.Squadra;
import it.uniroma3.siw.service.ServGiocatore;
import it.uniroma3.siw.service.ServSquadra;
import java.util.List;

@Controller
public class ControllerSquadra {

    @Autowired
    private ServSquadra squadraService;
    
    @Autowired
    private ServGiocatore giocatoreService;
    
    @GetMapping("/squadre")
    public String showSquadre(Model model) {
        List<Squadra> squadre = squadraService.findAllSquadre();
        model.addAttribute("elencoSquadre", squadre);
        return "squadre.html";
    }
    
    @GetMapping("/squadra/{id}")
    public String showSquadra(@PathVariable("id") Long id, Model model) {
        Squadra squadra = squadraService.findById(id);
        
        List<Giocatore> giocatori = giocatoreService.findBySquadra(squadra);
        
        model.addAttribute("squadra", squadra);
        model.addAttribute("giocatori", giocatori);
        
        return "squadra.html";
    }
}
