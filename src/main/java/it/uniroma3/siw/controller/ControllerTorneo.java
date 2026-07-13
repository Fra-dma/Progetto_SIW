package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import it.uniroma3.siw.model.ClassificaSquadra;
import it.uniroma3.siw.model.Torneo;
import it.uniroma3.siw.service.ServTorneo;
import java.util.List;

@Controller
public class ControllerTorneo {

    @Autowired
    private ServTorneo torneoService;

    @GetMapping("/tornei")
    public String showTornei(Model model) {
        List<Torneo> tornei = torneoService.findAllTornei();
        model.addAttribute("elencoTornei", tornei);
        return "tornei.html";
    }
    
    @GetMapping("/torneo/{id}/classifica")
    public String showClassifica(@PathVariable("id") Long id, Model model) {
        List<ClassificaSquadra> classifica = torneoService.calcolaClassifica(id);
        model.addAttribute("classifica", classifica);
        return "classifica.html";
    }
}
