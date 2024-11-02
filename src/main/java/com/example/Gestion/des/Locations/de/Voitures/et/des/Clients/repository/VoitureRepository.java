package com.example.Gestion.des.Locations.de.Voitures.et.des.Clients.repository;

import com.example.Gestion.des.Locations.de.Voitures.et.des.Clients.entities.Voiture;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoitureRepository extends CrudRepository<Voiture, Long> {}

