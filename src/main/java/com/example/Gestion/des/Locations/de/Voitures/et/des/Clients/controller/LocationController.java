package com.example.Gestion.des.Locations.de.Voitures.et.des.Clients.controller;

import com.example.Gestion.des.Locations.de.Voitures.et.des.Clients.entities.Location;
import com.example.Gestion.des.Locations.de.Voitures.et.des.Clients.entities.Voiture;
import com.example.Gestion.des.Locations.de.Voitures.et.des.Clients.repository.LocationRepository;
import com.example.Gestion.des.Locations.de.Voitures.et.des.Clients.repository.VoitureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class LocationController {

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private VoitureRepository voitureRepository;

    // Affiche le formulaire pour ajouter une location
    @GetMapping("/add-location")
    public String showAddLocationForm(Model model) {
        model.addAttribute("location", new Location());
        model.addAttribute("voitures", voitureRepository.findAll());
        return "add-location";
    }

    // Gère l'ajout de la location
    @PostMapping("/add-location")
    public String addLocation(@Valid @ModelAttribute("location") Location location, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("voitures", voitureRepository.findAll());
            return "add-location"; // Renvoyer au formulaire d'ajout si des erreurs de validation sont présentes
        }

        // Validation de la voiture sélectionnée
        if (location.getVoiture() == null || location.getVoiture().getId() <= 0) {
            result.rejectValue("voiture", "error.voiture", "Aucune voiture sélectionnée.");
            model.addAttribute("voitures", voitureRepository.findAll());
            return "add-location"; // Renvoyer au formulaire d'ajout si aucune voiture n'est sélectionnée
        }

        Voiture voiture = voitureRepository.findById(location.getVoiture().getId())
                .orElseThrow(() -> new IllegalArgumentException("ID de voiture invalide : " + location.getVoiture().getId()));

        // Vérification de la disponibilité de la voiture
        if (voiture.isDisponible()) {
            // Associer la voiture à la location et enregistrer
            location.setVoiture(voiture);
            locationRepository.save(location);

            // Mettre à jour le statut de disponibilité
            voiture.setDisponible(false); // La voiture devient indisponible après la location
            voitureRepository.save(voiture); // Sauvegarde de l'état de la voiture

            return "redirect:/location-detail"; // Redirige vers la page des détails des locations
        } else {
            // Si la voiture est déjà louée, ajouter un message d'erreur et rediriger vers la liste des voitures
            model.addAttribute("voitureDejaLouee", "Cette voiture est déjà louée."); // Message d'erreur
            model.addAttribute("voitures", voitureRepository.findAll()); // Récupère la liste des voitures
            return "list-voitures"; // Redirige vers la page de la liste des voitures
        }
    }



    // Affiche le formulaire pour mettre à jour une location
    @GetMapping("/update-location")
    public String showUpdateLocationForm(@RequestParam("id") long id, Model model) {
        Location location = locationRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID de location invalide :" + id));
        model.addAttribute("location", location);
        model.addAttribute("voitures", voitureRepository.findAll());
        return "update-location";
    }

    // Gère la mise à jour d'une location
    @PostMapping("/update-location")
    public String updateLocation(@RequestParam("id") long id, @Valid Location updatedLocation, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("voitures", voitureRepository.findAll());
            return "update-location";
        }

        Location location = locationRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID de location invalide :" + id));

        location.setDateDebut(updatedLocation.getDateDebut());
        location.setDateFin(updatedLocation.getDateFin());

        Voiture voiture = voitureRepository.findById(updatedLocation.getVoiture().getId())
                .orElseThrow(() -> new IllegalArgumentException("ID de voiture invalide : " + updatedLocation.getVoiture().getId()));
        location.setVoiture(voiture);

        locationRepository.save(location);
        return "redirect:/location-detail";
    }

    // Affiche la page de confirmation de suppression
    @GetMapping("/delete-location")
    public String confirmDeleteLocation(@RequestParam("id") long id, Model model) {
        Location location = locationRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID de location invalide :" + id));
        model.addAttribute("location", location);
        return "delete-location";
    }

    // Gère la suppression d'une location
    @PostMapping("/delete-location")
    public String deleteLocation(@RequestParam("id") long id) {
        Location location = locationRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID de location invalide :" + id));
        locationRepository.delete(location);
        return "redirect:/location-detail";
    }

    // Affiche le formulaire pour ajouter une location à partir de l'ID d'une voiture
    @GetMapping("/location/add")
    public String showAddLocationForm(@RequestParam("voitureId") Long voitureId, Model model) {
        Voiture voiture = voitureRepository.findById(voitureId)
                .orElseThrow(() -> new ResourceNotFoundException("Voiture non trouvée"));

        model.addAttribute("voiture", voiture);
        model.addAttribute("location", new Location());
        return "add-location";
    }
    // Affiche toutes les locations
    @GetMapping("/location-detail")
    public String listLocations(Model model) {
        model.addAttribute("locations", locationRepository.findAll());
        return "location-detail";
    }

    // Recherche les locations par client
    @PostMapping("/location-detail/search")
    public String searchLocationsByClient(@RequestParam("clientName") String clientName, Model model) {
        model.addAttribute("locations", locationRepository.findByClientContaining(clientName));
        model.addAttribute("clientName", clientName); // Pour garder le nom du client dans le formulaire
        return "location-detail"; // Renvoie à la même vue
    }
}