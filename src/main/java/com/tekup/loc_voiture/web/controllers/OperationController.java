package com.tekup.loc_voiture.web.controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tekup.loc_voiture.business.servicesimpl.ClientServiceImpl;
import com.tekup.loc_voiture.business.servicesimpl.OperationServiceImpl;
import com.tekup.loc_voiture.business.servicesimpl.VoitureServiceImpl;
import com.tekup.loc_voiture.dao.entities.Client;
import com.tekup.loc_voiture.dao.entities.OperationDetails;
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
    private VoitureServiceImpl voitureService;

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

    @GetMapping
    public String getOperations(Model model,
            @RequestParam(required = false) String idClient,
            @RequestParam(required = false) String idVoiture) {

        List<OperationLocation> operations;
        if (idClient != null && !idClient.isEmpty()) {
            Client client = clientService.getClientById(idClient).orElse(null);
            if (client != null) {
                operations = opService.findOperationsByClientId(client.getIdClient());
            } else {
                operations = Collections.emptyList();
            }
        } else if (idVoiture != null && !idVoiture.isEmpty()) {
            operations = opService.findOperationsByVoitureId(idVoiture);
        } else {
            operations = opService.getOperationsLocation();
        }

        List<OperationDetails> operationsDetails = new ArrayList<>();
        for (OperationLocation item : operations) {
            Voiture v = voitureService.getVoitureById(item.getIdVoiture()).get();
            Client c = clientService.getClientById(item.getIdClient()).get();
            operationsDetails.add(new OperationDetails(item.getIdOperation(), item.getDateDebut(), item.getDateFin(),
                    item.getTypeGarantie(), item.getFraisLocation(), item.getModePaiement(),
                    item.getMontantGarantie(), c.getNom() + " " + c.getPrenom(), v.getMarque() + " " + v.getModele()));
        }

        try {
            model.addAttribute("operationsDetails", operationsDetails);
            model.addAttribute("clients", clientService.getClients());
            model.addAttribute("voitures", voitureService.getVoitures());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return "listeOperations";
    }

    @PostMapping("/addOperation")
    public String saveOperationLocation(OperationLocationForm operationForm) {
        OperationLocation operation = new OperationLocation(
                operationForm.getDateDebut(),
                operationForm.getDateFin(),
                operationForm.getTypeGarantie(),
                operationForm.getModePaiement(),
                operationForm.getFraisLocation(),
                operationForm.getMontantGarantie(),
                operationForm.getIdClient(),
                operationForm.getIdVoiture());
        try {
            OperationLocation temp = opService.saveOperationLocation(operation);
        } catch (Exception e) {
            System.out.println(e.getMessage());

        }
        return "redirect:/dashboard/operations/addOperation";
    }

}
