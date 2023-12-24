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
        return operationRep.save(operation);
    }

}
