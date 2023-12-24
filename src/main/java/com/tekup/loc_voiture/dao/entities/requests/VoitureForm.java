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
    private String id_voiture;
    private String immatVoiture;
    private String marque;
    private String modele;
    private MultipartFile photo;
    private String dateMiseEnCirculation;
    private String prixLocation;
}
