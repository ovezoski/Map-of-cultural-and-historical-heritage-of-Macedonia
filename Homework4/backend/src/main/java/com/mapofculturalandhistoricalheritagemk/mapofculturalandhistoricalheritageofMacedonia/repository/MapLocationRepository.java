package com.mapofculturalandhistoricalheritagemk.mapofculturalandhistoricalheritageofMacedonia.repository;

import com.mapofculturalandhistoricalheritagemk.mapofculturalandhistoricalheritageofMacedonia.models.MapLocation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MapLocationRepository extends JpaRepository<MapLocation, String> {
    Page<MapLocation> findAll(Pageable pageable);
    Optional<MapLocation> findById(String id);
}
