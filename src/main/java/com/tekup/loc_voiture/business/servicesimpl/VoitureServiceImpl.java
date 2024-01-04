package com.tekup.loc_voiture.business.servicesimpl;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.tekup.loc_voiture.business.iservices.IVoitureService;
import com.tekup.loc_voiture.dao.entities.OperationLocation;
import com.tekup.loc_voiture.dao.entities.Voiture;
import com.tekup.loc_voiture.dao.repositories.VoitureRepository;

@Service
public class VoitureServiceImpl implements IVoitureService {
    @Value("${upload.path}")
    private String uploadPath;

    @Autowired
    private VoitureRepository vr;
    @Autowired
    private OperationServiceImpl op;

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

    public List<Voiture> getFilteredVoitures(List<Voiture> allVoitures, Double minPrice, Double maxPrice,
            String marque) {
        if ((minPrice == null || minPrice.equals(Double.NaN)) &&
                (maxPrice == null || maxPrice.equals(Double.NaN)) &&
                (marque == null || marque.trim().isEmpty())) {
            return allVoitures;
        }

        return allVoitures.stream()
                .filter(voiture -> (minPrice == null || Double.valueOf(voiture.getPrixLocation()) >= minPrice) &&
                        (maxPrice == null || Double.valueOf(voiture.getPrixLocation()) <= maxPrice) &&
                        (marque == null || marque.trim().isEmpty()
                                || voiture.getMarque().equalsIgnoreCase(marque.trim())))
                .collect(Collectors.toList());
    }

    public List<Voiture> getVoituresDispoByDate(String date, String voiture) {
        List<Voiture> voitures = getVoitures();

        if (date == null && voiture == null) {
            return voitures;
        }

        List<OperationLocation> operations = op.getOperationsLocation();
        List<OperationLocation> operationsAvailableByDate = getOperationsByDate(operations, date);
        if (!operationsAvailableByDate.isEmpty()) {
            for (OperationLocation operation : operations) {
                String voitureIdToRemove = operation.getIdVoiture();
                System.out.println(voitureIdToRemove);
                voitures.removeIf(v -> v.getIdVoiture().equals(voitureIdToRemove));
            }
        }

        // voitures = voitures.stream()
        // .filter(v -> voiture == null || voiture.isEmpty() ||
        // voiture.equalsIgnoreCase(v.getMarque() + " "
        // + v.getModele()))
        // .collect(Collectors.toList());

        return voitures;
    }

    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");

    public List<OperationLocation> getOperationsByDate(List<OperationLocation> operations, String date) {
        LocalDate targetDate = LocalDate.parse(date);

        return operations.stream()
                .filter(operation -> isDateInRange(targetDate, LocalDate.parse(operation.getDateDebut(), dateFormatter),
                        LocalDate.parse(operation.getDateFin(), dateFormatter)))
                .collect(Collectors.toList());
    }

    private boolean isDateInRange(LocalDate targetDate, LocalDate startDate, LocalDate endDate) {
        return targetDate.isBefore(startDate) || targetDate.isAfter(endDate);
    }

}
