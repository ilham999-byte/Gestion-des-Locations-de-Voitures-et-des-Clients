package com.example.Gestion.des.Locations.de.Voitures.et.des.Clients.controller;

import com.example.Gestion.des.Locations.de.Voitures.et.des.Clients.entities.Voiture;
import com.example.Gestion.des.Locations.de.Voitures.et.des.Clients.repository.VoitureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
public class VoitureController {

    @Autowired
    private VoitureRepository voitureRepository;

    // Affiche le formulaire d'ajout de voiture
    @GetMapping("/add-voiture")
    public String showAddVoitureForm(Model model) {
        model.addAttribute("voiture", new Voiture());
        return "add-voiture"; // Vue pour ajouter une voiture
    }

    // Ajoute une nouvelle voiture
    @PostMapping("/add-voiture")
    public String addVoiture(@Valid Voiture voiture, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-voiture"; // Renvoie au formulaire s'il y a des erreurs
        }

        voiture.setDisponible(true);
        voitureRepository.save(voiture);
        return "redirect:/list-voitures"; // Redirige vers la liste des voitures
    }

    // Affiche le formulaire de mise à jour d'une voiture
    @GetMapping("/update-voiture/{id}")
    public String showUpdateVoitureForm(@PathVariable("id") long id, Model model) {
        Voiture voiture = voitureRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID de voiture invalide :" + id));
        model.addAttribute("voiture", voiture);
        return "update-voiture"; // Vue pour le formulaire de mise à jour
    }

    // Met à jour une voiture
    @PostMapping("/update-voiture/{id}")
    public String updateVoiture(@PathVariable("id") long id, @Valid Voiture updatedVoiture, BindingResult result) {
        if (result.hasErrors()) {
            return "update-voiture"; // Renvoie au formulaire si des erreurs existent
        }

        Voiture voiture = voitureRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID de voiture invalide :" + id));
        voiture.setModele(updatedVoiture.getModele());
        voiture.setImmatriculation(updatedVoiture.getImmatriculation());
        voiture.setPrixLocation(updatedVoiture.getPrixLocation());
        // Optionnel : gérer le champ disponible
        voitureRepository.save(voiture);

        return "redirect:/list-voitures"; // Redirige vers la liste des voitures
    }

    // Affiche la page de confirmation de suppression d'une voiture
    @GetMapping("/delete-voiture/{id}")
    public String showDeleteVoitureConfirmation(@PathVariable("id") long id, Model model) {
        Voiture voiture = voitureRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID de voiture invalide :" + id));
        model.addAttribute("voiture", voiture);
        return "delete-voiture"; // Vue pour la confirmation de suppression
    }

    // Supprime une voiture
    @PostMapping("/delete-voiture/{id}")
    public String deleteVoiture(@PathVariable("id") long id) {
        Voiture voiture = voitureRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID de voiture invalide :" + id));
        voitureRepository.delete(voiture);
        return "redirect:/list-voitures"; // Redirige vers la liste des voitures
    }

    // Affiche la liste des voitures
    @GetMapping("/list-voitures")
    public String listVoitures(Model model) {
        model.addAttribute("voitures", voitureRepository.findAll());
        return "list-voitures";
    }

    @PostMapping("/list-voitures")
    public String postListVoitures(Model model) {
        model.addAttribute("voitures", voitureRepository.findAll());
        return "list-voitures"; // Renvoie à la vue de la liste des voitures
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }


}