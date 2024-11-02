package com.example.Gestion.des.Locations.de.Voitures.et.des.Clients.repository;



import com.example.Gestion.des.Locations.de.Voitures.et.des.Clients.entities.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository


public interface LocationRepository extends JpaRepository<Location, Long> {
    List<Location> findByClientContaining(String clientName); // Méthode pour rechercher par nom de client
}

