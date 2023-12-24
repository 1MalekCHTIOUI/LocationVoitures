package com.tekup.loc_voiture.dao.entities.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ClientForm {
    private String nom;
    private String prenom;
    private String adresse;
    private String telephone;
}
