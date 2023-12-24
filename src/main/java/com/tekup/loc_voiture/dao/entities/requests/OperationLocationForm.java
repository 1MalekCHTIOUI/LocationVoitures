package com.tekup.loc_voiture.dao.entities.requests;

import java.util.Date;

import com.tekup.loc_voiture.dao.entities.Client;
import com.tekup.loc_voiture.dao.entities.Voiture;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OperationLocationForm {
    private Date date_debut;
    private Date date_fin;
    private String type_garantie;
    private String mode_paiement;
    private String montant_garantie;
    private String frais_location;
    private Client client;
    private Voiture voiture;

}
