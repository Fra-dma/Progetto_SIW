package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import it.uniroma3.siw.model.Arbitro;
import it.uniroma3.siw.service.ServArbitro;
import java.util.List;

@Controller
public class ControllerArbitro {

    @Autowired
    private ServArbitro arbitroService;

}
