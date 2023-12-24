package com.tekup.loc_voiture.business.iservices;

import java.util.List;
import java.util.Optional;

import com.tekup.loc_voiture.dao.entities.OperationLocation;

public interface IOperationService {

    public List<OperationLocation> getOperationsLocation();

    public Optional<OperationLocation> getOperationLocation(String id);

    public OperationLocation saveOperationLocation(OperationLocation operation);
}
