package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import java.util.Base64;
import java.io.IOException;

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
        
        String currentUser = isLogged ? estraiNomeUtente(principal) : "";
        model.addAttribute("currentUser", currentUser);
        
        return "commenti_partita.html";
    }
    
    // Metodo di supporto
    // Questo metodo capisce se l'utente è standard
    // e ne estrae il nome in modo sicuro.
    private String estraiNomeUtente(Principal principal) {
        if (principal instanceof OAuth2AuthenticationToken) {
            OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) principal;
            String nome = oauthToken.getPrincipal().getAttribute("name"); // Google
            if (nome == null) {
                nome = oauthToken.getPrincipal().getAttribute("login"); // GitHub
            }
            return nome != null ? nome : oauthToken.getName();
        }
        return principal.getName();
    }
    
    @PostMapping("/partita/{id}/commento")
    public String aggiungiCommento(@PathVariable("id") Long idPartita, @RequestParam("testo") String testo, @RequestParam(value = "fileImmagine", required = false) MultipartFile file, Principal principal) {
        
    	String usernameLoggato = estraiNomeUtente(principal);	
        
        Utente autore = servUtente.findByUsername(usernameLoggato);
        
        if (autore == null) {
            autore = new Utente();
            autore.setUsername(usernameLoggato);
            servUtente.salvaUtente(autore); 
        }
        	
        Partita partita = servPartita.findById(idPartita);
        
        Commento nuovoCommento = new Commento();
        nuovoCommento.setTesto(testo);
        nuovoCommento.setAutore(autore);
        nuovoCommento.setPartita(partita);
        
        if (file != null && !file.isEmpty()) {
            try {
                // Trasforma l'immagine in testo Base64
                String immagineCodificata = Base64.getEncoder().encodeToString(file.getBytes());
                nuovoCommento.setImmagineBase64(immagineCodificata);
            } catch (IOException e) {
                System.out.println("Errore durante il caricamento dell'immagine nel nuovo commento.");
            }
        }
        
        servCommento.salvaCommento(nuovoCommento);
        
        return "redirect:/partita/" + idPartita + "/commenti"; 
    }

    @PostMapping("/commento/{id}/modifica")
    public String salvaModificaCommento(@PathVariable("id") Long id, @RequestParam("testo") String testo, @RequestParam(value = "fileImmagine", required = false) MultipartFile file, Principal principal) {
        if (principal == null) {
            return "redirect:/login";
        }
        
        Commento commento = servCommento.findById(id);
        
        // Aggiornato con estraiNomeUtente
        if (commento.getAutore() != null && commento.getAutore().getUsername().equals(estraiNomeUtente(principal))) {
            commento.setTesto(testo);
            
            if (file != null && !file.isEmpty()) {
                try {
                    String immagineCodificata = Base64.getEncoder().encodeToString(file.getBytes());
                    commento.setImmagineBase64(immagineCodificata);
                } catch (IOException e) {
                    System.out.println("Errore durante l'aggiornamento dell'immagine.");
                }
            }
            
            servCommento.salvaCommento(commento);
        }
        
        return "redirect:/partita/" + commento.getPartita().getId() + "/commenti";
    }
    
    @GetMapping("/commento/{id}/modifica")
    public String formModificaCommento(@PathVariable("id") Long id, Model model, Principal principal) {
        if (principal == null) {
            return "redirect:/login";
        }
        
        Commento commento = servCommento.findById(id); 
        
        if (commento.getAutore() == null || !commento.getAutore().getUsername().equals(estraiNomeUtente(principal))) {
            return "redirect:/partita/" + commento.getPartita().getId() + "/commenti";
        }
        
        model.addAttribute("commento", commento);
        return "modifica_commento.html";
    }

}
