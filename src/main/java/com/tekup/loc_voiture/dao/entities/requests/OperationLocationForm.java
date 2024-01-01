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
    private String dateDebut;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String dateFin;
    private String typeGarantie;
    private String fraisLocation;
    private String modePaiement;
    private String montantGarantie;
    private String idClient;
    private String idVoiture;
    private boolean operationFinished;

    public boolean getOperationFinished() {
        return this.operationFinished;
    }

    public void setOperationFinished(boolean operationFinished) {
        this.operationFinished = operationFinished;
    }
}
