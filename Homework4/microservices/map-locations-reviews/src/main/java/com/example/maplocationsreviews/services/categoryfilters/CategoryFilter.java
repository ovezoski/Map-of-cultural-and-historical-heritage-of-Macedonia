package com.example.maplocationsreviews.services.categoryfilters;

import com.example.maplocationsreviews.models.MapLocation;
public interface CategoryFilter {
    boolean filter(MapLocation mapLocation, String category);
}
