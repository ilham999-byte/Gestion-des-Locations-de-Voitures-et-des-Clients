package com.example.Gestion.des.Locations.de.Voitures.et.des.Clients.entities;

import jakarta.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;

@Entity
public class Voiture {
    @OneToMany(mappedBy = "voiture", cascade = CascadeType.ALL)
    private List<Location> locations; // Suppression en cascade

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotBlank(message = "Le modèle est obligatoire")
    private String modele;

    @NotBlank(message = "L'immatriculation est obligatoire")
    @Column(unique = true)
    private String immatriculation;

    @NotNull(message = "Le prix de location est obligatoire")
    @Positive(message = "Le prix de location doit être positif")
    private Double prixLocation;

    @NotNull(message = "Le statut de disponibilité est obligatoire")
    private Boolean disponible; // Ajout du champ statut de disponibilité

    public Voiture() {
        super();
        this.disponible = true; // Par défaut, la voiture est disponible
    }


    public Voiture(String modele, String immatriculation, Double prixLocation) {
        this.modele = modele;
        this.immatriculation = immatriculation;
        this.prixLocation = prixLocation;
        this.disponible = true; // Par défaut, la voiture est disponible
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getModele() {
        return modele;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }

    public String getImmatriculation() {
        return immatriculation;
    }

    public void setImmatriculation(String immatriculation) {
        this.immatriculation = immatriculation;
    }

    public Double getPrixLocation() {
        return prixLocation;
    }

    public void setPrixLocation(Double prixLocation) {
        this.prixLocation = prixLocation;
    }

    public Boolean getDisponible() {
        return disponible;
    }

    public void setDisponible(Boolean disponible) {
        this.disponible = disponible;
    }

    public boolean isDisponible() {
        return disponible != null ? disponible : false; // Retourne false si disponible est null
    }
}