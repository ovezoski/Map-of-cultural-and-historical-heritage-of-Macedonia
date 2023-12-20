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
            @RequestParam(required = false) String searchTerm,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String city,
            @PageableDefault(size = 10) Pageable pageable) {

        Page<MapLocation> entities;

        if (searchTerm != null || category != null || city != null) {
            entities = mapLocationService.searchBy(searchTerm, category, city, pageable);
        } else {
            entities = mapLocationRepository.findAll(pageable);
        }

        return ResponseEntity.ok(entities);
    }
//    @GetMapping("/")
//    public ResponseEntity<Page<MapLocation>> getEntities(@PageableDefault(size = 10) Pageable pageable) {
//        Page<MapLocation> entities = mapLocationRepository.findAll(pageable);
//        return ResponseEntity.ok(entities);
//    }
//
//    @GetMapping("/")
//    public ResponseEntity<Page<MapLocation>> getEntitiesFilter(@RequestParam String searchTerm,
//            @RequestParam String category,
//            @RequestParam String city,
//            @PageableDefault(size = 10) Pageable pageable) {
//        Page<MapLocation> entities = mapLocationService.searchBy(searchTerm, category, city, pageable);
////                mapLocationRepository.findAll(pageable);
//        return ResponseEntity.ok(entities);
//    }

}
