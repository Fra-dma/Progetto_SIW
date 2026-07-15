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
import it.uniroma3.siw.service.ServTorneo;

import java.security.Principal;
import java.util.List;

@Controller
public class ControllerTorneo {

    @Autowired
    private ServTorneo servTorneo;
   

    @GetMapping("/tornei")
    public String showTornei(Model model, Principal principal) {
        List<Torneo> tornei = servTorneo.findAllTornei();
        
     // 2. Logica per verificare se è Admin
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
    public String showClassifica(@PathVariable("id") Long id, Model model) {
        List<ClassificaSquadra> classifica = servTorneo.calcolaClassifica(id);
        model.addAttribute("classifica", classifica);
        return "classifica.html";
    }
    
    @GetMapping("/torneo/{id}/partite")
    public String showPartite(@PathVariable("id") Long id, Model model) {
        List<Partita> partite = servTorneo.findAllPartite(id);
        model.addAttribute("elencoPartite", partite);
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
