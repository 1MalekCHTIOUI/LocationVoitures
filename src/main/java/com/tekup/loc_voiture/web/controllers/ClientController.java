package com.tekup.loc_voiture.web.controllers;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tekup.loc_voiture.business.servicesimpl.ClientServiceImpl;
import com.tekup.loc_voiture.dao.entities.Client;
import com.tekup.loc_voiture.dao.entities.Voiture;
import com.tekup.loc_voiture.dao.entities.requests.ClientForm;
import com.tekup.loc_voiture.dao.entities.requests.VoitureForm;

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

    @GetMapping
    public String showListeClients(Model model) {
        List<Client> clients = clientService.getClients();

        model.addAttribute("clients", clients);

        return "listeClients";
    }

    @GetMapping("/{id}/edit")
    public String showEditClient(@PathVariable("id") String id, Model model) {
        Optional<Client> clientEdit = clientService.getClientById(id);
        if (clientEdit.isPresent()) {
            model.addAttribute("clientForm",
                    new Client(clientEdit.get().getNom(), clientEdit.get().getPrenom(), clientEdit.get().getAdresse(),
                            clientEdit.get().getTelephone()));
            model.addAttribute("id", id);
        }
        return "editClient";
    }

    @PostMapping("/{id}/edit")
    public String editVoiture(@PathVariable("id") String id,
            @ModelAttribute("clientForm") ClientForm clientForm)
            throws IOException {
        Client client = new Client(clientForm.getNom(), clientForm.getPrenom(), clientForm.getAdresse(),
                clientForm.getTelephone());
        clientService.editClient(id, client);

        return "redirect:/dashboard/clients";

    }

    @PostMapping("/{id}/delete")
    public String deleteClient(@PathVariable("id") String id) {
        clientService.deleteClient(id);
        return "redirect:/dashboard/clients";
    }
}
