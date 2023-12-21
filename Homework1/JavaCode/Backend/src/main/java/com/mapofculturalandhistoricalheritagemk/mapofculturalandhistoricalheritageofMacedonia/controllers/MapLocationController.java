package com.mapofculturalandhistoricalheritagemk.mapofculturalandhistoricalheritageofMacedonia.controllers;

import com.mapofculturalandhistoricalheritagemk.mapofculturalandhistoricalheritageofMacedonia.models.MapLocation;
import com.mapofculturalandhistoricalheritagemk.mapofculturalandhistoricalheritageofMacedonia.repository.MapLocationRepository;

import com.mapofculturalandhistoricalheritagemk.mapofculturalandhistoricalheritageofMacedonia.services.MapLocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/map-locations")
@CrossOrigin("*")
@RequiredArgsConstructor
public class MapLocationController {
    private final MapLocationRepository mapLocationRepository;
    private final MapLocationService mapLocationService;

    @GetMapping("/")
    public ResponseEntity<Page<MapLocation>> getEntities(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String amenity, //TODO CHANGE BACK TO category
            @RequestParam(required = false) String city,
            @PageableDefault(size = 10) Pageable pageable) {

        Page<MapLocation> entities;
        //TODO CHANGE TO != null IN IF STATEMENT, THIS IS TEMPORARY FIX TO WORK WITH FRONT END
        if (name != null || !amenity.equals( "undefined") || !city.isEmpty()) {
            entities = mapLocationService.searchBy(name, amenity, city, pageable);
        } else {
            entities = mapLocationService.findAll(pageable);
        }

        return ResponseEntity.ok(entities);
    }
}
