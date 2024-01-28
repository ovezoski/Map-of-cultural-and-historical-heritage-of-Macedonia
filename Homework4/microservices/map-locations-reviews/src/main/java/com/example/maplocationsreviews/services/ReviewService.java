package com.example.maplocationsreviews.services;

import com.example.maplocationsreviews.models.Review;

import java.util.List;
import java.util.Optional;

public interface ReviewService {
    void removeReview(String mapLocationId, Integer reviewToDeleteId);

    void deleteById(Integer id);

    List<Review> findByMapLocation(String idMapLocation);

    void save(float score, String description, String idMapLocation , String authName);

    Optional<Review> findById(int reviewId);
}
