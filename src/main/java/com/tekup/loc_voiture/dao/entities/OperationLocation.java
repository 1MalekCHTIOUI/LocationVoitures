package com.tekup.loc_voiture.dao.entities;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Document
public class OperationLocation {
    @Id
    private String id_operation;
    private String date_debut;
    private String date_fin;
    private String type_garantie;
    private String frais_location;
    private String mode_paiement;
    private String montant_garantie;
    private String id_client;
    private String id_voiture;

    public OperationLocation(String date_debut, String date_fin, String type_garantie, String frais_location,
            String mode_paiement, String montant_garantie, String id_client, String id_voiture) {
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.type_garantie = type_garantie;
        this.frais_location = frais_location;
        this.mode_paiement = mode_paiement;
        this.montant_garantie = montant_garantie;
        this.id_client = id_client;
        this.id_voiture = id_voiture;
    }
}
