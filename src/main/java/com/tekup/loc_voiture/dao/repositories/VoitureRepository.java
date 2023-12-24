package com.tekup.loc_voiture.dao.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.tekup.loc_voiture.dao.entities.Voiture;

import java.util.Optional;

public interface VoitureRepository extends MongoRepository<Voiture, String> {
    Optional<Voiture> findVoitureByImmatVoiture(String immatVoiture);
}
