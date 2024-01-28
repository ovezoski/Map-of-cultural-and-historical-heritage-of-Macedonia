package com.example.maplocationsreviews.controllers;

import com.example.maplocationsreviews.models.MapLocation;
import com.example.maplocationsreviews.models.Review;
import com.example.maplocationsreviews.models.dtos.EditMapLocationDTO;
import com.example.maplocationsreviews.models.dtos.NewReviewDTO;
import com.example.maplocationsreviews.services.MapLocationService;
import com.example.maplocationsreviews.services.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/map-locations")
@CrossOrigin("*")
@RequiredArgsConstructor
public class MapLocationController {

    private final MapLocationService mapLocationService;
    private final ReviewService reviewService;

    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @GetMapping("/")
    public ResponseEntity<Page<MapLocation>> getEntities(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String city,
            @RequestParam(required = false) String latitude,
            @RequestParam(required = false) String longitude,
            @PageableDefault(size = 50) Pageable pageable
    ) {
        Page<MapLocation> entities = mapLocationService.searchBy(name, category, city, pageable, latitude, longitude);
        return ResponseEntity.ok(entities);
    }


    @GetMapping("/{id}/reviews")
    public List<Review> getReviews(@PathVariable("id") String idMapLocation) {
        return reviewService.findByMapLocation(idMapLocation);
    }

    @PostMapping("/{id}/addReview")
    public void addReview(@PathVariable("id") String idMapLocation, @RequestBody NewReviewDTO body, Authentication authentication) {
        reviewService.save(body.getScore(), body.getDescription(), idMapLocation, authentication.getName());
    }

    @DeleteMapping("/{id}/deleteReview/{reviewId}")
    public void deleteReview(@PathVariable("id") String idMapLocation, @PathVariable("reviewId") int idReview) {
        reviewService.removeReview(idMapLocation, idReview);
    }

    @PutMapping("/{id}")
    public void editReview(@PathVariable("id") String idMapLocation, @RequestBody EditMapLocationDTO body) {
        mapLocationService.editMapLocation(idMapLocation, body.getTitle());
    }

}
