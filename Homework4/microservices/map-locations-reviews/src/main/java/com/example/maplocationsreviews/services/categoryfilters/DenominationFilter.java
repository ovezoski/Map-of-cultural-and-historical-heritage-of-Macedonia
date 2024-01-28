package com.example.maplocationsreviews.services.categoryfilters;

import com.example.maplocationsreviews.models.MapLocation;
public class DenominationFilter implements CategoryFilter{
    private DenominationFilter() {

    }
    private static DenominationFilter denominationFilter;

    //Singleton with double-checked locking
    public static DenominationFilter getInstance() {
        if (denominationFilter == null) {
            synchronized (DenominationFilter.class) {
                if (denominationFilter == null) {
                    denominationFilter = new DenominationFilter();
                }
            }
        }
        return denominationFilter;
    }
    @Override
    public boolean filter(MapLocation mapLocation, String category) {
        return mapLocation.getDenomination() != null && mapLocation.getDenomination().equalsIgnoreCase(category);
    }
}
