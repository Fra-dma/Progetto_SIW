package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.siw.model.Giocatore;
import it.uniroma3.siw.model.Squadra;
import it.uniroma3.siw.service.ServGiocatore;
import it.uniroma3.siw.service.ServSquadra;
import java.util.List;

@Controller
public class ControllerSquadra {

    @Autowired
    private ServSquadra servSquadra;
    
    @Autowired
    private ServGiocatore servGiocatore;
    
    @GetMapping("/squadre")
    public String showSquadre(Model model) {
        List<Squadra> squadre = servSquadra.findAllSquadre();
        model.addAttribute("elencoSquadre", squadre);
        return "squadre.html";
    }
    
    @GetMapping("/squadra/{id}")
    public String showSquadra(@PathVariable("id") Long id, Model model) {
        Squadra squadra = servSquadra.findById(id);
        
        List<Giocatore> giocatori = servGiocatore.findBySquadra(squadra);
        
        model.addAttribute("squadra", squadra);
        model.addAttribute("giocatori", giocatori);
        
        return "squadra.html";
    }
    
    @GetMapping("/admin/squadra/nuova")
    public String formNuovaSquadra(Model model) {
        model.addAttribute("squadra", new Squadra());
        
        List<Giocatore> tuttiIGiocatori = servGiocatore.findAllGiocatori();
        model.addAttribute("elencoGiocatori", tuttiIGiocatori);
        
        return "admin/formNuovaSquadra"; 
    }

    @PostMapping("/admin/squadra/nuova")
    public String salvaSquadra(@ModelAttribute("squadra") Squadra squadra) {
        
        // 1. Salviamo prima la squadra per generare il suo ID nel database
        Squadra squadraSalvata = servSquadra.salvaSquadra(squadra);
        
        // 2. Se l'admin ha selezionato dei giocatori dalla lista delle checkbox...
        if (squadra.getGiocatori() != null) {
            for (Giocatore g : squadra.getGiocatori()) {
                // ... diciamo a ogni giocatore qual è la sua nuova squadra
                g.setSquadra(squadraSalvata);
                // Salviamo l'aggiornamento del giocatore nel database
                servGiocatore.salvaGiocatore(g); 
            }
        }
        
        return "redirect:/admin/dashboard";
    }
    
    @GetMapping("/ricerca_squadre")
    public String mostraPaginaRicercaReact() {
        return "ricerca_squadre.html";
    }
}
