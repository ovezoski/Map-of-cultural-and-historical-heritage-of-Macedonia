package com.mapofculturalandhistoricalheritagemk.mapofculturalandhistoricalheritageofMacedonia.controllers;

import com.mapofculturalandhistoricalheritagemk.mapofculturalandhistoricalheritageofMacedonia.models.MapLocation;
import com.mapofculturalandhistoricalheritagemk.mapofculturalandhistoricalheritageofMacedonia.models.Review;
import com.mapofculturalandhistoricalheritagemk.mapofculturalandhistoricalheritageofMacedonia.models.dtos.EditMapLocationDTO;
import com.mapofculturalandhistoricalheritagemk.mapofculturalandhistoricalheritageofMacedonia.models.dtos.NewReviewDTO;
import com.mapofculturalandhistoricalheritagemk.mapofculturalandhistoricalheritageofMacedonia.repository.MapLocationRepository;

import com.mapofculturalandhistoricalheritagemk.mapofculturalandhistoricalheritageofMacedonia.repository.ReviewRepository;
import com.mapofculturalandhistoricalheritagemk.mapofculturalandhistoricalheritageofMacedonia.services.MapLocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/map-locations")
@CrossOrigin("*")
@RequiredArgsConstructor
public class MapLocationController {
    private final MapLocationService mapLocationService;
    private final ReviewRepository reviewRepository;

    @GetMapping("/")
    public ResponseEntity<Page<MapLocation>> getEntities(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String city,
            @PageableDefault(size = 10) Pageable pageable
    ) {

        Page<MapLocation> entities;

        entities = mapLocationService.searchBy(name, category, city, pageable);

        return ResponseEntity.ok(entities);
    }

    @GetMapping("/{id}/reviews")
    public List<Review> getReviews(@PathVariable String id) {
        MapLocation currLocation = mapLocationService.findById(id).orElseThrow(NoSuchElementException::new);
        return reviewRepository.findByMapLocation(currLocation);
    }

    @PostMapping("/{id}/addReview")
    public void addReview(@PathVariable String id, @RequestBody NewReviewDTO body, Authentication authentication) {
        MapLocation currLocation = mapLocationService.findById(id).orElseThrow(NoSuchElementException::new);
        Review newReview = new Review(0, body.getScore(), body.getDescription(), currLocation, authentication.getName());
        reviewRepository.save(newReview);
        mapLocationService.addReview(currLocation, newReview);
    }

    @DeleteMapping("/{id}/deleteReview/{reviewId}")
    public void deleteReview(@PathVariable String id, @PathVariable int reviewId) {
        MapLocation currLocation = mapLocationService.findById(id).orElseThrow(NoSuchElementException::new);
        Review currReview = reviewRepository.findById(reviewId).orElseThrow(NoSuchElementException::new);
        mapLocationService.removeReview(currLocation, currReview);
        reviewRepository.deleteById(reviewId);
    }

    @PutMapping("/{id}")
    public void editReview(@PathVariable String id, @RequestBody EditMapLocationDTO body) {
        MapLocation currLocation = mapLocationService.findById(id).orElseThrow(NoSuchElementException::new);
        mapLocationService.editMapLocation(currLocation, body.getTitle());
    }
}
