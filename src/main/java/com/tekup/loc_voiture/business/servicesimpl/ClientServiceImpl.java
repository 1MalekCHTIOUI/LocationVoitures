package com.tekup.loc_voiture.business.servicesimpl;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.tekup.loc_voiture.business.iservices.IClientService;
import com.tekup.loc_voiture.dao.entities.Client;
import com.tekup.loc_voiture.dao.entities.Voiture;
import com.tekup.loc_voiture.dao.repositories.ClientRepository;

@Service
public class ClientServiceImpl implements IClientService {

    @Autowired
    private ClientRepository clientRep;

    @Override
    public List<Client> getClients() {
        return clientRep.findAll();
    }

    @Override
    public Optional<Client> getClientById(String id) {
        return clientRep.findById(id);
    }

    @Override
    public Client saveClient(Client client) {
        return clientRep.save(client);
    }

    @Override
    public Client editClient(String id, Client client) {
        Optional<Client> optionalClient = this.getClientById(id);

        if (optionalClient.isPresent()) {
            Client existingClient = optionalClient.get();
            existingClient.setNom(client.getNom());
            existingClient.setPrenom(client.getPrenom());
            existingClient.setAdresse(client.getAdresse());
            existingClient.setTelephone(client.getTelephone());
            return clientRep.save(existingClient);
        } else {
            throw new IllegalArgumentException("Client with id: " + id + " not found");
        }
    }

    @Override
    public void deleteClient(String id) {
        clientRep.deleteById(id);
    }

}
