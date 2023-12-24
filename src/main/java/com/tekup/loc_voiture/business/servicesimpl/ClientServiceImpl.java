package com.tekup.loc_voiture.business.servicesimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tekup.loc_voiture.business.iservices.IClientService;
import com.tekup.loc_voiture.dao.entities.Client;
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
    public Optional<Client> getClient(String id) {
        return clientRep.findById(id);
    }

    @Override
    public Client saveClient(Client client) {
        return clientRep.save(client);
    }

}
