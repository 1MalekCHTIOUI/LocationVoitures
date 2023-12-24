package com.tekup.loc_voiture.dao.repositories;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.tekup.loc_voiture.dao.entities.User;

public interface UserRepository extends MongoRepository<User, String> {

	Optional<User> findUserByEmail(String email);
}