package com.tekup.loc_voiture.business.iservices;

import java.util.List;
import java.util.Optional;

import com.tekup.loc_voiture.dao.entities.OperationLocation;

public interface IOperationService {

    public List<OperationLocation> getOperationsLocation();

    public Optional<OperationLocation> getOperationLocation(String id);

    public Optional<OperationLocation> findOperationByClientId(String id);

    public Optional<OperationLocation> findOperationByVoitureId(String id);

    public List<OperationLocation> findOperationsByClientId(String id);

    public List<OperationLocation> findOperationsByVoitureId(String id);

    public OperationLocation saveOperationLocation(OperationLocation operation);

    public OperationLocation editOperationLocation(String id, OperationLocation operation);
}
