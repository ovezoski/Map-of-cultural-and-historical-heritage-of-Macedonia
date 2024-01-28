package com.example.maplocationsreviews.repository;

import com.example.maplocationsreviews.models.MapLocation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MapLocationRepository extends JpaRepository<MapLocation, String> {
    Page<MapLocation> findAll(Pageable pageable);
    Optional<MapLocation> findById(String id);
}
