package com.tekup.loc_voiture.dao.entities;

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
    private String idOperation;
    private String dateDebut;
    private String dateFin;
    private String typeGarantie;
    private String fraisLocation;
    private String modePaiement;
    private String montantGarantie;
    private String idClient;
    private String idVoiture;
    private boolean operationFinished;

    public OperationLocation(String dateDebut, String dateFin, String typeGarantie, String fraisLocation,
            String modePaiement, String montantGarantie, String idClient, String idVoiture, boolean operationFinished) {
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.typeGarantie = typeGarantie;
        this.fraisLocation = fraisLocation;
        this.modePaiement = modePaiement;
        this.montantGarantie = montantGarantie;
        this.idClient = idClient;
        this.idVoiture = idVoiture;
        this.operationFinished = operationFinished;
    }

    public boolean getOperationFinished() {
        return this.operationFinished;
    }

    public void setOperationFinished(boolean operationFinished) {
        this.operationFinished = operationFinished;
    }
}
