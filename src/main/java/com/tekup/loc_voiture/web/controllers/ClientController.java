package com.tekup.loc_voiture.web.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tekup.loc_voiture.business.servicesimpl.ClientServiceImpl;
import com.tekup.loc_voiture.dao.entities.Client;
import com.tekup.loc_voiture.dao.entities.requests.ClientForm;

@Controller
@RequestMapping("/dashboard/clients")
public class ClientController {

    @Autowired
    private ClientServiceImpl clientService;

    @GetMapping("/addClient")
    public String addClient(Model model) {
        ClientForm clientForm = new ClientForm();
        model.addAttribute("clientForm", clientForm);
        return "addClient";
    }

    @PostMapping("/addClient")
    public String saveClient(ClientForm clientForm) {
        Client client = new Client(
                clientForm.getNom(),
                clientForm.getPrenom(),
                clientForm.getAdresse(),
                clientForm.getTelephone());

        clientService.saveClient(client);
        return "redirect:/dashboard/clients/addClient";
    }

}
