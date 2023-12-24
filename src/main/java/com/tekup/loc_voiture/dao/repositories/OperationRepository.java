package com.tekup.loc_voiture.dao.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.tekup.loc_voiture.dao.entities.OperationLocation;

public interface OperationRepository extends MongoRepository<OperationLocation, String> {

}
