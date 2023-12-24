package com.tekup.loc_voiture.business.iservices;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import com.tekup.loc_voiture.dao.entities.Voiture;

public interface IVoitureService {

    public List<Voiture> getVoitures();

    public Optional<Voiture> getVoitureByImmat(String immat);

    public Optional<Voiture> getVoitureById(String id);

    public Voiture addVoiture(Voiture voiture, MultipartFile file) throws IOException;

    public void deleteVoiture(String id);
}
