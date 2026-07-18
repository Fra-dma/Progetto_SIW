	package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.siw.model.ClassificaSquadra;
import it.uniroma3.siw.model.Partita;
import it.uniroma3.siw.model.Torneo;
import it.uniroma3.siw.model.Utente;
import it.uniroma3.siw.service.ServTorneo;
import it.uniroma3.siw.service.ServUtente;

import java.security.Principal;
import java.util.List;

@Controller
public class ControllerTorneo {

    @Autowired
    private ServTorneo servTorneo;
    
    @Autowired
    private ServUtente servUtente;
   
    @GetMapping("/")
    public String redirectToHome() {
        // vera pagina home :)
        return "redirect:/tornei"; 
    }

    @GetMapping("/tornei")
    public String showTornei(Model model, Principal principal) {
        List<Torneo> tornei = servTorneo.findAllTornei();
        
     // Logica per verificare se è Admin
        boolean isAdmin = false;
        if (principal != null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            isAdmin = auth.getAuthorities().stream()
                          .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
        }
        model.addAttribute("isAdmin", isAdmin);
        
        model.addAttribute("elencoTornei", tornei);
        model.addAttribute("isLogged", principal != null);
        return "tornei.html";
    }
    
    @GetMapping("/torneo/{id}/classifica")
    public String showClassifica(@PathVariable("id") Long id, Model model, Principal principal) {
    	
    	servTorneo.eseguiAnalisiSperimentale(id);
        
        // Recupero il torneo per mostrare il suo nome nella pagina HTML
        Torneo torneo = servTorneo.findById(id);
        
        // Passo direttamente l'ID del torneo
        List<ClassificaSquadra> classificaCalcolata = servTorneo.calcolaClassifica(id);
        
        // Passo la classifica elaborata
        model.addAttribute("classifica", classificaCalcolata);
        model.addAttribute("torneo", torneo);
        
        // Controllo permessi per i pulsanti di rimozione (Admin)
        boolean isAdmin = false;
        if (principal != null) {
            Utente utenteLoggato = servUtente.findByUsername(principal.getName());
            if (utenteLoggato != null && "ADMIN".equals(utenteLoggato.getRuolo())) {
                isAdmin = true;
            }
        }
        
        model.addAttribute("isAdmin", isAdmin);
        
        return "classifica.html";
    }
    
    @GetMapping("/torneo/{id}/partite")
    public String showPartite(@PathVariable("id") Long id, Model model, Principal principal) {
        
        Torneo torneo = servTorneo.findById(id);
        
        List<Partita> partite = torneo.getPartite();
        model.addAttribute("elencoPartite", partite); 
       
        boolean isAdmin = false;
        if (principal != null) {
            Utente utenteLoggato = servUtente.findByUsername(principal.getName());
            if (utenteLoggato != null && "ADMIN".equals(utenteLoggato.getRuolo())) {
                isAdmin = true;
            }
        }
        
        model.addAttribute("isAdmin", isAdmin);
        model.addAttribute("torneo", torneo);
        
        return "partite.html";
    }
    
    @GetMapping("/admin/torneo/nuovo")
    public String formNuovoTorneo(Model model) {
        model.addAttribute("torneo", new Torneo());
        return "admin/formNuovoTorneo.html";
    }
    
    @PostMapping("/admin/torneo/nuovo")
    public String salvaTorneo(@ModelAttribute("torneo") Torneo torneo) {
        servTorneo.salvaTorneo(torneo);
        return "redirect:/admin/dashboard";
    }
    
    @GetMapping("/admin/torneo/{id}/gestisciPartite")
    public String gestisciPartite(@PathVariable("id") Long id, Model model) {
        Torneo torneo = servTorneo.findById(id);
        model.addAttribute("torneo", torneo);
        return "admin/gestisciPartite.html"; 
    }
    
}
