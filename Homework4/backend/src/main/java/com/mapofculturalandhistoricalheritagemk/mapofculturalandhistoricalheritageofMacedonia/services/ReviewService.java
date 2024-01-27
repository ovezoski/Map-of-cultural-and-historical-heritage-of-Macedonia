package com.mapofculturalandhistoricalheritagemk.mapofculturalandhistoricalheritageofMacedonia.services;

import com.mapofculturalandhistoricalheritagemk.mapofculturalandhistoricalheritageofMacedonia.models.MapLocation;
import com.mapofculturalandhistoricalheritagemk.mapofculturalandhistoricalheritageofMacedonia.models.Review;

import java.util.List;
import java.util.Optional;

public interface ReviewService {
    void addReview(MapLocation mapLocation, Review newReview);

    void removeReview(MapLocation mapLocation, Review reviewToDelete);
    void deleteById(Integer id);

    List<Review> findByMapLocation(MapLocation currLocation);

    void save(Review newReview);

    Optional<Review> findById(int reviewId);
}
