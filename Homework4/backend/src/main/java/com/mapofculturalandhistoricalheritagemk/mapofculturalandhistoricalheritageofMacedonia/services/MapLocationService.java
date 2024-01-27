package com.mapofculturalandhistoricalheritagemk.mapofculturalandhistoricalheritageofMacedonia.services;

import com.mapofculturalandhistoricalheritagemk.mapofculturalandhistoricalheritageofMacedonia.models.MapLocation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;


public interface MapLocationService {
    List<MapLocation> getAllLocations();

    Page<MapLocation> searchBy(String name, String category, String city, Pageable pageable,
                                      String latitude, String longitude);

    Optional<MapLocation> findById(String id);

    void editMapLocation(MapLocation mapLocation, String title);

    Long count();

    List<MapLocation> saveAll(List<MapLocation> mapLocations);
    MapLocation save(MapLocation ml);
}
