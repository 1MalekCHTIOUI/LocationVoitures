package com.tekup.loc_voiture.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tekup.loc_voiture.business.servicesimpl.VoitureServiceImpl;
import com.tekup.loc_voiture.dao.entities.Voiture;
import com.tekup.loc_voiture.dao.entities.requests.VoitureForm;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.lang.InterruptedException;

@Controller
@RequestMapping
public class VoitureController {

    @Autowired
    private VoitureServiceImpl voitureService;

    @GetMapping({ "/", "/home" })
    // @RequestMapping({"/","/voitures"})
    public String getVoitures(Model model) {
        List<Voiture> voitures = voitureService.getVoitures();

        model.addAttribute("voitures", voitures);
        return "index";
    }

    @GetMapping("/voitures/{immatVehicle}")
    public String getVoiture(Model model, @PathVariable("immatVehicle") String immat) {
        Optional<Voiture> v = voitureService.getVoitureByImmat(immat);
        if (v.isPresent()) {
            model.addAttribute("voiture", v.get());
            return "detailVoiture";
        } else {
            return "Voiture non existent";
        }

    }

    @GetMapping("/dashboard/voitures/addVoiture")
    public String showAddVoitureForm() {
        return "addVoiture";
    }

    @PostMapping("/dashboard/voitures/addVoiture")
    public String addVoiture(@ModelAttribute("voitureForm") VoitureForm voitureForm) {
        Voiture v = new Voiture(voitureForm.getImmatVoiture(), voitureForm.getMarque(), voitureForm.getModele(),
                voitureForm.getDateMiseEnCirculation(), voitureForm.getPrixLocation(), true);
        try {
            voitureService.addVoiture(v, voitureForm.getPhoto());
            // Thread.sleep(2000);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return "redirect:/dashboard/voitures?refresh=true";
    }

    @GetMapping("/dashboard/voitures")
    public String showListeVoiture(Model model,
            @RequestParam(name = "refresh", defaultValue = "false") boolean refresh) {
        List<Voiture> voitures = voitureService.getVoitures();
        model.addAttribute("refresh", refresh);
        try {
            model.addAttribute("voitures", voitures);
            Thread.sleep(1000);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return "listeVoiture";
    }

    @PostMapping("/dashboard/voitures/{id}/delete")
    public String deleteVoiture(Model model, @PathVariable("id") String id) {
        voitureService.deleteVoiture(id);
        return "redirect:/dashboard/voitures";
    }

    @GetMapping("/dashboard/voitures/{id}/edit")
    public String showEditVoiture(@PathVariable("id") String id, Model model) {
        Optional<Voiture> voitureEdit = voitureService.getVoitureById(id);
        if (voitureEdit.isPresent()) {
            model.addAttribute("voitureForm",
                    new Voiture(voitureEdit.get().getImmatVoiture(), voitureEdit.get().getMarque(),
                            voitureEdit.get().getModele(), voitureEdit.get().getPhoto(),
                            voitureEdit.get().getDateMiseEnCirculation(), voitureEdit.get().getPrixLocation(),
                            voitureEdit.get().getIsAvailable()));

            model.addAttribute("id", id);
        } else {
            System.out.println("nope");
        }
        return "editVoiture";
    }

    @PostMapping("/dashboard/voitures/{id}/edit")
    public String editVoiture(@PathVariable("id") String id, @ModelAttribute("voitureForm") VoitureForm voitureForm)
            throws IOException {
        Voiture voiture = new Voiture(voitureForm.getImmatVoiture(),
                voitureForm.getMarque(),
                voitureForm.getModele(),
                voitureForm.getDateMiseEnCirculation(), voitureForm.getPrixLocation(), voitureForm.getIsAvailable());
        voitureService.editVoiture(id, voiture, voitureForm.getPhoto());

        return "redirect:/dashboard/voitures?refresh=true";

    }

}