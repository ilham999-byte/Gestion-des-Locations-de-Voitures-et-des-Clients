package com.example.Gestion.des.Locations.de.Voitures.et.des.Clients.entities;

import jakarta.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull(message = "La date de début est obligatoire")
    @Column(nullable = false)
    private LocalDate dateDebut;

    @NotNull(message = "La date de fin est obligatoire")
    @Column(nullable = false)
    private LocalDate dateFin;

    @ManyToOne
    @JoinColumn(name = "voiture.id", nullable = false)
    private Voiture voiture;

    private String client;

    public Location() {
        super();
    }

    public Location(LocalDate dateDebut, LocalDate dateFin, Voiture voiture, String client) {
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.voiture = voiture;
        this.client = client;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDate dateFin) {
        if (dateDebut != null && dateFin.isBefore(this.dateDebut)) {
            throw new IllegalArgumentException("La date de fin doit être après la date de début.");
        }
        this.dateFin = dateFin;
    }

    public Voiture getVoiture() {
        return voiture;
    }

    public void setVoiture(Voiture voiture) {
        this.voiture = voiture;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }
}
