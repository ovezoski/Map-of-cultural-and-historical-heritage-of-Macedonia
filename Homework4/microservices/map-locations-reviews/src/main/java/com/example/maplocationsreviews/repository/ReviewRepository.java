package com.example.maplocationsreviews.repository;

import com.example.maplocationsreviews.models.MapLocation;
import com.example.maplocationsreviews.models.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
    List<Review> findByMapLocationId(String mapLocationId);
}
