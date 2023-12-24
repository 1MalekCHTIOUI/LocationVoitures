package com.tekup.loc_voiture.dao.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.tekup.loc_voiture.dao.entities.Client;

public interface ClientRepository extends MongoRepository<Client, String> {

}
