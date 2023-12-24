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

import com.tekup.loc_voiture.business.servicesimpl.OperationServiceImpl;
import com.tekup.loc_voiture.dao.entities.OperationLocation;
import com.tekup.loc_voiture.dao.entities.requests.OperationLocationForm;

@Controller
@RequestMapping("/operations")
public class OperationController {

    @Autowired
    private OperationServiceImpl opService;

    // @GetMapping
    // public String getOperationLocation(Model model) {
    // List<OperationLocation> ops = opService.getOperationsLocation();

    // model.addAttribute("operations", ops);
    // return "index";
    // }

    // @GetMapping("/{id}")
    // public String getOperationLocation(Model model, @PathVariable String id) {
    // Optional<OperationLocation> op = opService.getOperationLocation(id);
    // if (op.isPresent()) {
    // model.addAttribute("operation", op.get());
    // return "detailOperation";
    // } else {
    // return "Operation non existent";
    // }
    // }
    @GetMapping("/addOperation")
    public String addOperationLocation(Model model) {
        OperationLocationForm operationForm = new OperationLocationForm();
        model.addAttribute("operationForm", operationForm);
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
                operationForm.getClient().getId_client(),
                operationForm.getVoiture().getId_voiture());

        OperationLocation op = opService.saveOperationLocation(operation);
        return "redirect:/operations/" + op.getId_operation();
    }

}
