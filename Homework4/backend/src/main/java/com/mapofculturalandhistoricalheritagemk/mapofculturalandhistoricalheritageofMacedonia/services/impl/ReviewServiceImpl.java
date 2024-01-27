package com.mapofculturalandhistoricalheritagemk.mapofculturalandhistoricalheritageofMacedonia.services.impl;

import com.mapofculturalandhistoricalheritagemk.mapofculturalandhistoricalheritageofMacedonia.models.MapLocation;
import com.mapofculturalandhistoricalheritagemk.mapofculturalandhistoricalheritageofMacedonia.models.Review;
import com.mapofculturalandhistoricalheritagemk.mapofculturalandhistoricalheritageofMacedonia.repository.MapLocationRepository;
import com.mapofculturalandhistoricalheritagemk.mapofculturalandhistoricalheritageofMacedonia.repository.ReviewRepository;
import com.mapofculturalandhistoricalheritagemk.mapofculturalandhistoricalheritageofMacedonia.services.MapLocationService;
import com.mapofculturalandhistoricalheritagemk.mapofculturalandhistoricalheritageofMacedonia.services.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private final MapLocationService mapLocationService;
    private final ReviewRepository reviewRepository;

    public void addReview(MapLocation mapLocation, Review newReview) {
        mapLocation.getReviews().add(newReview);
        mapLocationService.save(mapLocation);
    }

    public void removeReview(MapLocation mapLocation, Review reviewToDelete) {
        mapLocation.getReviews().remove(reviewToDelete);
        mapLocationService.save(mapLocation);
    }
    public void deleteById(Integer id){
        reviewRepository.deleteById(id);
    }

    public List<Review> findByMapLocation(MapLocation currLocation) {
        return reviewRepository.findByMapLocation(currLocation);
    }

    public void save(Review newReview) {
        reviewRepository.save(newReview);
    }

    public Optional<Review> findById(int reviewId) {
        return reviewRepository.findById(reviewId);
    }
}
