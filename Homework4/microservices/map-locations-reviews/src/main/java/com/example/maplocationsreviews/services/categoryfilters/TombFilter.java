package com.example.maplocationsreviews.services.categoryfilters;

import com.example.maplocationsreviews.models.MapLocation;
public class TombFilter implements CategoryFilter{
    private TombFilter() {

    }
    private static TombFilter tombFilter;

    //Singleton with double-checked locking
    public static TombFilter getInstance() {
        if (tombFilter == null) {
            synchronized (TombFilter.class) {
                if (tombFilter == null) {
                    tombFilter = new TombFilter();
                }
            }
        }
        return tombFilter;
    }
    @Override
    public boolean filter(MapLocation mapLocation, String category) {
        return mapLocation.getTomb() != null && mapLocation.getTomb().equalsIgnoreCase(category);
    }
}
