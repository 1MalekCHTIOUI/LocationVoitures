package com.tekup.loc_voiture.web.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tekup.loc_voiture.business.servicesimpl.ClientServiceImpl;
import com.tekup.loc_voiture.business.servicesimpl.OperationServiceImpl;
import com.tekup.loc_voiture.business.servicesimpl.VoitureServiceImp;
import com.tekup.loc_voiture.dao.entities.Client;
import com.tekup.loc_voiture.dao.entities.OperationLocation;
import com.tekup.loc_voiture.dao.entities.Voiture;
import com.tekup.loc_voiture.dao.entities.requests.OperationLocationForm;

@Controller
@RequestMapping("/dashboard/operations")
public class OperationController {

    @Autowired
    private OperationServiceImpl opService;
    @Autowired
    private ClientServiceImpl clientService;
    @Autowired
    private VoitureServiceImp voitureService;

    @GetMapping("/addOperation")
    public String addOperationLocationForm(Model model) {
        List<Client> listClients = clientService.getClients();
        OperationLocationForm operationForm = new OperationLocationForm();
        List<Voiture> voitures = voitureService.getVoitures();
        model.addAttribute("operationForm", operationForm);
        model.addAttribute("clients", listClients);
        model.addAttribute("voitures", voitures);
        return "addOperation";
    }

    @PostMapping("/addOperation")
    public String saveOperationLocation(OperationLocationForm operationForm) {
        OperationLocation operation = new OperationLocation(
                operationForm.getDate_debut(),
                operationForm.getDate_fin(),
                operationForm.getType_garantie(),
                operationForm.getMode_paiement(),
                operationForm.getFrais_location(),
                operationForm.getMontant_garantie(),
                operationForm.getId_client(),
                operationForm.getId_voiture());

        System.out.println(operation.getId_voiture());
        try {
            OperationLocation temp = opService.saveOperationLocation(operation);
            System.out.println(temp.getId_voiture());
        } catch (Exception e) {
            System.out.println(e.getMessage());

        }
        return "redirect:/dashboard/operations/addOperation";
    }

}
