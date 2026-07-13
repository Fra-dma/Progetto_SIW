package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import it.uniroma3.siw.model.Partita;
import it.uniroma3.siw.service.ServPartita;
import java.util.List;

@Controller
public class ControllerPartita {

    @Autowired
    private ServPartita partitaService;

}
