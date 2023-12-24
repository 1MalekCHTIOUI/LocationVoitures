package com.tekup.loc_voiture.business.iservices;

import java.util.List;
import java.util.Optional;
import com.tekup.loc_voiture.dao.entities.Client;

public interface IClientService {
    public List<Client> getClients();

    public Optional<Client> getClient(String id);

    public Client saveClient(Client client);

}
