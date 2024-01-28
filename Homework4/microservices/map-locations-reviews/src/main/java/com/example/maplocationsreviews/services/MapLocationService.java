package com.example.maplocationsreviews.services;

import com.example.maplocationsreviews.models.MapLocation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;


public interface MapLocationService {
    List<MapLocation> getAllLocations();

    Page<MapLocation> searchBy(String name, String category, String city, Pageable pageable,
                                      String latitude, String longitude);

    Optional<MapLocation> findById(String id);

    void editMapLocation(String mapLocationId, String title);

    Long count();

    List<MapLocation> saveAll(List<MapLocation> mapLocations);

    MapLocation save(MapLocation ml);
}
