package com.mapofculturalandhistoricalheritagemk.mapofculturalandhistoricalheritageofMacedonia.services;

import com.mapofculturalandhistoricalheritagemk.mapofculturalandhistoricalheritageofMacedonia.models.MapLocation;
import com.mapofculturalandhistoricalheritagemk.mapofculturalandhistoricalheritageofMacedonia.models.Review;
import com.mapofculturalandhistoricalheritagemk.mapofculturalandhistoricalheritageofMacedonia.repository.MapLocationRepository;
import com.mapofculturalandhistoricalheritagemk.mapofculturalandhistoricalheritageofMacedonia.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final MapLocationRepository mapLocationRepository;
    private final ReviewRepository reviewRepository;

    public void addReview(MapLocation mapLocation, Review newReview) {
        mapLocation.getReviews().add(newReview);
        mapLocationRepository.save(mapLocation);
    }

    public void removeReview(MapLocation mapLocation, Review reviewToDelete) {
        mapLocation.getReviews().remove(reviewToDelete);
        mapLocationRepository.save(mapLocation);
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
