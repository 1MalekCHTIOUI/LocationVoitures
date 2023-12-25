package com.tekup.loc_voiture.dao.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.tekup.loc_voiture.dao.entities.OperationLocation;

public interface OperationRepository extends MongoRepository<OperationLocation, String> {

    Optional<OperationLocation> findOperationByIdClient(String id);

    Optional<OperationLocation> findOperationByIdVoiture(String id);

    List<OperationLocation> findOperationsByIdClient(String id);

    List<OperationLocation> findOperationsByIdVoiture(String id);
}
