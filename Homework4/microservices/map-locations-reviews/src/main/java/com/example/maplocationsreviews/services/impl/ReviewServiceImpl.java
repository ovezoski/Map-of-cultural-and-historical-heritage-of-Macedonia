package com.example.maplocationsreviews.services.impl;

import com.example.maplocationsreviews.models.MapLocation;
import com.example.maplocationsreviews.models.Review;
import com.example.maplocationsreviews.repository.ReviewRepository;
import com.example.maplocationsreviews.services.MapLocationService;
import com.example.maplocationsreviews.services.ReviewService;
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
        return reviewRepository.findByMapLocationId(idMapLocation);
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
