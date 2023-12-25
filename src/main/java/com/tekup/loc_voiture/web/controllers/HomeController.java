package com.tekup.loc_voiture.web.controllers;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.tekup.loc_voiture.business.servicesimpl.OperationServiceImpl;
import com.tekup.loc_voiture.business.servicesimpl.VoitureServiceImpl;
import com.tekup.loc_voiture.dao.entities.OperationLocation;
import com.tekup.loc_voiture.dao.entities.Voiture;

@Controller
public class HomeController {

	@Autowired
	private OperationServiceImpl opService;

	@Autowired
	private VoitureServiceImpl voitureService;

	@GetMapping("/dashboard")
	public String getAdminPage(Model model) {
		int total = calculateTotalEarnings(opService.getOperationsLocation());
		List<Voiture> rentedCars = getCurrentlyRentedCars(opService.getOperationsLocation());
		LocalDate currentDate = LocalDate.now();
		Month currentMonth = currentDate.getMonth();
		model.addAttribute("totalEarnings", total);
		model.addAttribute("rentedCars", rentedCars);
		model.addAttribute("currentMonth", currentMonth);
		model.addAttribute("currentDate", currentDate);
		return "dashboard";
	}

	@GetMapping("/access-denied")
	public String getAccessDeniedPage() {
		return "access-denied-page";
	}

	public int calculateTotalEarnings(List<OperationLocation> opList) {
		int total = 0;
		for (OperationLocation op : opList) {
			total = total + Integer.parseInt(op.getFraisLocation());
		}
		return total;
	}

	public List<Voiture> getCurrentlyRentedCars(List<OperationLocation> opList) {
		List<Voiture> rentedCars = new ArrayList<>();
		LocalDate currentDate = LocalDate.now();
		int currentMonth = currentDate.getMonthValue();

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd"); // Create formatter for "YYYY/MM/DD"
																					// format

		for (OperationLocation op : opList) {
			LocalDate startDate = LocalDate.parse(op.getDateDebut(), formatter); // Parse date string
			LocalDate endDate = LocalDate.parse(op.getDateFin(), formatter); // Parse date string

			if (startDate.getMonthValue() <= currentMonth && endDate.getMonthValue() >= currentMonth) {
				Voiture v = voitureService.getVoitureById(op.getIdVoiture()).orElse(null);
				rentedCars.add(v);
			}
		}

		return rentedCars;
	}
}
