package com.tekup.loc_voiture.dao.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(collection = "voitures")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Voiture {
    @Id
    private String idVoiture;
    private String immatVoiture;
    private String marque;
    private String modele;
    private String photo;
    private String dateMiseEnCirculation;
    private String prixLocation;
    private boolean isAvailable;

    public Voiture(String immatVoiture, String marque, String modele, String photo, String dateMiseEnCirculation,
            String prixLocation, boolean isAvailable) {
        this.immatVoiture = immatVoiture;
        this.marque = marque;
        this.modele = modele;
        this.photo = photo;
        this.dateMiseEnCirculation = dateMiseEnCirculation;
        this.prixLocation = prixLocation;
        this.isAvailable = isAvailable;
    }

    // constructeur voiture avec sans image
    public Voiture(String immatVoiture, String marque, String modele, String dateMiseEnCirculation,
            String prixLocation, boolean isAvailable) {
        this.immatVoiture = immatVoiture;
        this.marque = marque;
        this.modele = modele;
        this.dateMiseEnCirculation = dateMiseEnCirculation;
        this.prixLocation = prixLocation;
        this.isAvailable = isAvailable;
    }

    public boolean getIsAvailable() {
        return this.isAvailable;
    }

    public void setIsAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

}
