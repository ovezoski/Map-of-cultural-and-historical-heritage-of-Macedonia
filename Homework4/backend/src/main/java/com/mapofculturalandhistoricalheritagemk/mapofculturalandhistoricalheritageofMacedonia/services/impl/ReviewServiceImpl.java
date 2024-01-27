package com.mapofculturalandhistoricalheritagemk.mapofculturalandhistoricalheritageofMacedonia.services.impl;

import com.mapofculturalandhistoricalheritagemk.mapofculturalandhistoricalheritageofMacedonia.models.MapLocation;
import com.mapofculturalandhistoricalheritagemk.mapofculturalandhistoricalheritageofMacedonia.models.Review;
import com.mapofculturalandhistoricalheritagemk.mapofculturalandhistoricalheritageofMacedonia.repository.ReviewRepository;
import com.mapofculturalandhistoricalheritagemk.mapofculturalandhistoricalheritageofMacedonia.services.MapLocationService;
import com.mapofculturalandhistoricalheritagemk.mapofculturalandhistoricalheritageofMacedonia.services.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private final MapLocationService mapLocationService;
    private final ReviewRepository reviewRepository;

    public void removeReview(String mapLocationId, Integer reviewToDeleteId) {
        MapLocation currLocation = mapLocationService.findById(mapLocationId).orElseThrow(NoSuchElementException::new);
        Review currReview = this.findById(reviewToDeleteId).orElseThrow(NoSuchElementException::new);
        currLocation.getReviews().remove(currReview);
        this.deleteById(reviewToDeleteId);
        mapLocationService.save(currLocation);
    }
    public void deleteById(Integer id){
        reviewRepository.deleteById(id);
    }

    public List<Review> findByMapLocation(String idMapLocation) {
        MapLocation currLocation = mapLocationService.findById(idMapLocation).orElseThrow(NoSuchElementException::new);
        return reviewRepository.findByMapLocation(currLocation);
    }

    public void save(float score, String description, String idMapLocation, String authName) {
        MapLocation currLocation = mapLocationService.findById(idMapLocation).orElseThrow(NoSuchElementException::new);
        Review newReview = new Review(0, score, description, currLocation, authName);
        reviewRepository.save(newReview);
        currLocation.getReviews().add(newReview);
        mapLocationService.save(currLocation);
    }


    public Optional<Review> findById(int reviewId) {
        return reviewRepository.findById(reviewId);
    }
}
