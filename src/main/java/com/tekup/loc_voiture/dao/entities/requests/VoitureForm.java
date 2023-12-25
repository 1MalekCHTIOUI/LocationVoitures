package com.tekup.loc_voiture.dao.entities.requests;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VoitureForm {
    private String immatVoiture;
    private String marque;
    private String modele;
    private MultipartFile photo;
    private String dateMiseEnCirculation;
    private String prixLocation;
    private boolean isAvailable;

    public boolean getIsAvailable() {
        return this.isAvailable;
    }

    public void setIsAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }
}
