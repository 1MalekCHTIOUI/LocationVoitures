package com.tekup.loc_voiture.dao.entities.requests;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

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
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String date_debut;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String date_fin;
    private String type_garantie;
    private String mode_paiement;
    private String montant_garantie;
    private String frais_location;
    private String id_client;
    private String id_voiture;

}
