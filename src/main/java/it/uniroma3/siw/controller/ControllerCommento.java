package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.uniroma3.siw.model.Commento;
import it.uniroma3.siw.model.Partita;
import it.uniroma3.siw.model.Utente;
import it.uniroma3.siw.service.ServCommento;
import it.uniroma3.siw.service.ServPartita;
import it.uniroma3.siw.service.ServUtente;

import java.security.Principal;
import java.util.List;

@Controller
public class ControllerCommento {

    @Autowired
    private ServCommento servCommento;

    @Autowired
    private ServPartita servPartita;
    
    @Autowired
    private ServUtente servUtente;

    @GetMapping("/partita/{id}/commenti")
    public String showCommentiPartita(@PathVariable("id") Long id, Model model, Principal principal) {
        
        Partita partita = servPartita.findById(id);
        
        List<Commento> commenti = servCommento.findByPartita(partita);
        
        model.addAttribute("partita", partita);
        model.addAttribute("commenti", commenti);
        
        boolean isLogged = (principal != null);
        model.addAttribute("isLogged", isLogged);
        
        return "commenti_partita.html";
    }
    
    @PostMapping("/partita/{id}/commento")
    public String aggiungiCommento(@PathVariable("id") Long idPartita,  @RequestParam("testo") String testo, Principal principal) {
        
        String usernameLoggato = principal.getName();
        
        Utente autore = servUtente.findByUsername(usernameLoggato);
        Partita partita = servPartita.findById(idPartita);
        
        Commento nuovoCommento = new Commento();
        nuovoCommento.setTesto(testo);
        nuovoCommento.setAutore(autore);
        nuovoCommento.setPartita(partita);
        
        servCommento.salvaCommento(nuovoCommento);
        
        return "redirect:/partita/" + idPartita + "/commenti"; 
    }
}
