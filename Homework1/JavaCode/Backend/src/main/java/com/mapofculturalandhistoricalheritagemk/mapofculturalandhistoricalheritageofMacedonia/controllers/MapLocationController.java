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
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String city,
            @RequestParam(required = false) String latitude,
            @RequestParam(required = false) String longitude,
            @PageableDefault(size = 50) Pageable pageable
    ) {

        Page<MapLocation> entities;

        entities = mapLocationService.searchBy(name, category, city, pageable, latitude, longitude);

        return ResponseEntity.ok(entities);
    }
}
