package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.uniroma3.siw.model.Partita;
import it.uniroma3.siw.model.Torneo;
import it.uniroma3.siw.service.ServArbitro;
import it.uniroma3.siw.service.ServPartita;
import it.uniroma3.siw.service.ServSquadra;
import it.uniroma3.siw.service.ServTorneo;

@Controller
public class ControllerAdmin {
	
	@Autowired
    private ServTorneo servTorneo;
    @Autowired
    private ServSquadra servSquadra;
    @Autowired
    private ServArbitro servArbitro;
    @Autowired
    private ServPartita servPartita;

    @GetMapping("/admin/dashboard")
    public String showAdminDashboard(Model model) {
    	model.addAttribute("elencoTornei", servTorneo.findAllTornei());
        return "admin/adminDashboard";
    }
    
    @GetMapping("/admin/tornei/selezione")
    public String selezionaTorneo(Model model) {
        model.addAttribute("elencoTornei", servTorneo.findAllTornei());
        return "admin/selezionaTorneo";
    }

    @GetMapping("/admin/torneo/modifica/{id}")
    public String formModificaTorneo(@PathVariable("id") Long id, Model model) {
        model.addAttribute("torneo", servTorneo.findById(id));
        model.addAttribute("tutteLeSquadre", servSquadra.findAllSquadre());
        model.addAttribute("tuttiGliArbitri", servArbitro.findAll());
        model.addAttribute("nuovaPartita", new Partita());
        return "admin/editTorneo";
    }

    //Salva modifiche torneo
    @PostMapping("/admin/torneo/modifica")
    public String salvaModificaTorneo(@ModelAttribute("torneo") Torneo torneo) {
        servTorneo.salvaTorneo(torneo);
        return "redirect:/admin/dashboard";
    }

    //Crea partita
    @PostMapping("/admin/torneo/{id}/aggiungiPartita")
    public String aggiungiPartita(@PathVariable("id") Long idTorneo, 
                                  @ModelAttribute("nuovaPartita") Partita partita,
                                  @RequestParam("squadraCasaId") Long idCasa,
                                  @RequestParam("squadraOspiteId") Long idOspite,
                                  @RequestParam("arbitroId") Long idArbitro) {
    	
    	partita.setId(null);
        
        Torneo torneo = servTorneo.findById(idTorneo);
        partita.setTorneo(torneo);
        partita.setSquadraCasa(servSquadra.findById(idCasa));
        partita.setSquadraOspite(servSquadra.findById(idOspite));
        partita.setArbitro(servArbitro.findById(idArbitro));
        
        servPartita.salvaPartita(partita);
        
        return "redirect:/admin/torneo/modifica/" + idTorneo;
    }
	
}
