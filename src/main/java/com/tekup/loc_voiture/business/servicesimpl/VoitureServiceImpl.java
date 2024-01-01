package com.tekup.loc_voiture.business.servicesimpl;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.tekup.loc_voiture.business.iservices.IVoitureService;
import com.tekup.loc_voiture.dao.entities.Voiture;
import com.tekup.loc_voiture.dao.repositories.VoitureRepository;

@Service
public class VoitureServiceImpl implements IVoitureService {
    @Value("${upload.path}")
    private String uploadPath;

    @Autowired
    private VoitureRepository vr;

    @Override
    public List<Voiture> getVoitures() {
        return vr.findAll();
    }

    @Override
    public Optional<Voiture> getVoitureByImmat(String immat) {
        return vr.findVoitureByImmatVoiture(immat);
    }

    @Override
    public Optional<Voiture> getVoitureById(String id) {
        return vr.findById(id);
    }

    @Override
    public Voiture addVoiture(Voiture voiture, MultipartFile file) throws IOException {
        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        File uploadFile = new File(uploadPath, fileName);
        file.transferTo(uploadFile);

        voiture.setPhoto(fileName);
        System.out.println(voiture);
        return vr.save(voiture);
    }

    @Override
    public void deleteVoiture(String id) {
        vr.deleteById(id);
    }

    public Voiture editVoiture(String id, Voiture voiture, MultipartFile file) throws IOException {
        Optional<Voiture> optionalVoiture = this.getVoitureById(id);

        if (optionalVoiture.isPresent()) {
            Voiture existingVoiture = optionalVoiture.get();
            existingVoiture.setImmatVoiture(voiture.getImmatVoiture());
            existingVoiture.setMarque(voiture.getMarque());
            existingVoiture.setModele(voiture.getModele());
            existingVoiture.setPrixLocation(voiture.getPrixLocation());
            existingVoiture.setIsAvailable(voiture.getIsAvailable());
            if (!file.getOriginalFilename().isEmpty()
                    && !existingVoiture.getPhoto().equals(file.getOriginalFilename())) {
                String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
                File uploadFile = new File(uploadPath, fileName);
                file.transferTo(uploadFile);
                existingVoiture.setPhoto(fileName);
                System.out.println(voiture.getMarque());
            }
            return vr.save(existingVoiture);
        } else {
            throw new IllegalArgumentException("Voiture not found with id: " + id);
        }
    }

}
