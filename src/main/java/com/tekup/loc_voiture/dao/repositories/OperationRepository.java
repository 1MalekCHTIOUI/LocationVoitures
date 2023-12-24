package com.tekup.loc_voiture.dao.repositories;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.tekup.loc_voiture.dao.entities.OperationLocation;

public interface OperationRepository extends MongoRepository<OperationLocation, String> {

    Optional<OperationLocation> findOperationById_client(String id);

    Optional<OperationLocation> findOperationById_voiture(String id);
}
