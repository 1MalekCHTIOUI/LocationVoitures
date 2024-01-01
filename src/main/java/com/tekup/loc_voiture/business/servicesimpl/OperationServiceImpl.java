package com.tekup.loc_voiture.business.servicesimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tekup.loc_voiture.business.iservices.IOperationService;
import com.tekup.loc_voiture.dao.entities.OperationLocation;
import com.tekup.loc_voiture.dao.repositories.OperationRepository;

@Service
public class OperationServiceImpl implements IOperationService {

    @Autowired
    private OperationRepository operationRep;

    @Override
    public List<OperationLocation> getOperationsLocation() {
        return operationRep.findAll();
    }

    @Override
    public Optional<OperationLocation> getOperationLocation(String id) {
        return operationRep.findById(id);
    }

    @Override
    public OperationLocation saveOperationLocation(OperationLocation operation) {
        operation.setOperationFinished(false);
        return operationRep.save(operation);
    }

    @Override
    public Optional<OperationLocation> findOperationByClientId(String id) {
        return operationRep.findOperationByIdClient(id);
    }

    @Override
    public List<OperationLocation> findOperationsByClientId(String id) {
        return operationRep.findOperationsByIdClient(id);
    }

    @Override
    public Optional<OperationLocation> findOperationByVoitureId(String id) {
        return operationRep.findOperationByIdVoiture(id);

    }

    @Override
    public List<OperationLocation> findOperationsByVoitureId(String id) {
        return operationRep.findOperationsByIdVoiture(id);
    }

    @Override
    public OperationLocation editOperationLocation(String id, OperationLocation operation) {
        Optional<OperationLocation> operationOptional = operationRep.findById(id);
        if (operationOptional.isPresent()) {
            OperationLocation operationLocation = operationOptional.get();
            operationLocation.setDateDebut(operation.getDateDebut());
            operationLocation.setDateFin(operation.getDateFin());
            operationLocation.setFraisLocation(operation.getFraisLocation());
            operationLocation.setIdVoiture(operation.getIdVoiture());
            operationLocation.setIdClient(operation.getIdClient());
            operationLocation.setModePaiement(operation.getModePaiement());
            operationLocation.setTypeGarantie(operation.getTypeGarantie());
            operationLocation.setMontantGarantie(operation.getMontantGarantie());
            operationLocation.setOperationFinished(operation.getOperationFinished());
            return operationRep.save(operationLocation);
        }
        return null;
    }

}
