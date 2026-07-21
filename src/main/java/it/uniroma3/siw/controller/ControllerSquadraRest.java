package it.uniroma3.siw.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.uniroma3.siw.model.Squadra;
import it.uniroma3.siw.service.ServSquadra;

// L'annotazione @RestController per restituire dati JSON
@RestController
@RequestMapping("/api/squadre")
public class ControllerSquadraRest {

    @Autowired
    private ServSquadra servSquadra;

    @GetMapping
    public List<Squadra> getAllSquadre() {
        List<Squadra> squadre = (List<Squadra>) servSquadra.findAllSquadre(); 
        return squadre;
    }
    
}
