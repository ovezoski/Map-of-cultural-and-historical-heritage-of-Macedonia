package com.example.maplocationsreviews.services.filters;

import com.example.maplocationsreviews.models.MapLocation;

import java.util.List;

public interface Filter {
    List<MapLocation> filter(List<MapLocation> mapLocationList, String criteria);
}
