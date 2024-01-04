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
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping
public class VoitureController {

    @Autowired
    private VoitureServiceImpl voitureService;

    @GetMapping({ "/", "/home" })
    public String getLimitedVoitures(Model model) {
        List<Voiture> voitures = voitureService.getVoitures();
        List<Voiture> selectedVoitures = voitures.stream().limit(6).collect(Collectors.toList());
        model.addAttribute("voitures", selectedVoitures);
        return "index";
    }

    @GetMapping("/voitures")
    public String getAllVoitures(Model model, @RequestParam(name = "minPrice", required = false) Double minPrice,
            @RequestParam(name = "maxPrice", required = false) Double maxPrice,
            @RequestParam(name = "marque", required = false) String marque) {
        List<Voiture> voitures = voitureService.getVoitures();

        Set<String> voituresNoOcc = new HashSet<>();

        if (minPrice != null || maxPrice != null || marque != null) {
            System.out.println("minPrice: " + minPrice + " maxPrice: " + maxPrice + " marque: " + marque);
            voitures = voitureService.getFilteredVoitures(voitures, minPrice, maxPrice, marque);
        } else {
            voitures = voitureService.getVoitures();
        }

        model.addAttribute("voitures", voitures);

        for (Voiture voiture : voitureService.getVoitures()) {
            String marqueV = voiture.getMarque();
            if (!voituresNoOcc.contains(marqueV)) {
                voituresNoOcc.add(marqueV);
            }
        }
        model.addAttribute("voituresNoOcc", voituresNoOcc);
        return "listeVoitureClient";
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
            model.addAttribute("v", voitureEdit.get().getMarque() + " " + voitureEdit.get().getModele());
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

    @GetMapping("/dashboard/voitures/dispo")
    public String getVoituresDispo(Model model, @RequestParam(name = "date", required = false) String date,
            @RequestParam(name = "voiture", required = false) String voiture) {

        List<Voiture> voitures = voitureService.getVoitures();
        if (date != null || voiture != null) {
            voitures = voitureService.getVoituresDispoByDate(date, voiture);
        } else {
            voitures = voitureService.getVoitures();
        }
        model.addAttribute("voituresDispo", voitures);
        model.addAttribute("voitures", voitureService.getVoitures());

        return "listeVoituresParDate";
    }

}