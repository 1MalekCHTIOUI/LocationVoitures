package com.tekup.loc_voiture.dao.entities;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Data;

@Data
@Document(collection = "users")
public class User {

	@Id
	@Field
	private String id;

	@Field
	private String name;

	@Field
	private String password;

	@Field
	private String email;

	@Field
	private List<String> roles;

}