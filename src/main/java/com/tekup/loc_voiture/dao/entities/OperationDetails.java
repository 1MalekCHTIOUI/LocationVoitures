package com.tekup.loc_voiture.dao.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OperationDetails {
    private String idOperation;
    private String dateDebut;
    private String dateFin;
    private String typeGarantie;
    private String fraisLocation;
    private String modePaiement;
    private String montantGarantie;
    private String client;
    private String voiture;
    private boolean operationFinished;

}
