package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import it.uniroma3.siw.model.Giocatore;
import it.uniroma3.siw.service.ServGiocatore;
import java.util.List;

@Controller
public class ControllerGiocatore {

    @Autowired
    private ServGiocatore giocatoreService;

    @GetMapping("/giocatori")
    public String showGiocatori(Model model) {
        List<Giocatore> giocatori = giocatoreService.findAllGiocatori();
        model.addAttribute("elencoGiocatori", giocatori);
        return "giocatori.html";
    }
}
