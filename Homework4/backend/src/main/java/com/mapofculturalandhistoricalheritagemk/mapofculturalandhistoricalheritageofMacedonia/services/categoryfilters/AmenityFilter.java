package com.mapofculturalandhistoricalheritagemk.mapofculturalandhistoricalheritageofMacedonia.services.categoryfilters;

import com.mapofculturalandhistoricalheritagemk.mapofculturalandhistoricalheritageofMacedonia.models.MapLocation;

public class AmenityFilter implements CategoryFilter{

    private AmenityFilter() {

    }
    private static AmenityFilter amenityFilter;

    //Singleton with double-checked locking
    public static AmenityFilter getInstance() {
        if (amenityFilter == null) {
            synchronized (AmenityFilter.class) {
                if (amenityFilter == null) {
                    amenityFilter = new AmenityFilter();
                }
            }
        }
        return amenityFilter;
    }
    @Override
    public boolean filter(MapLocation mapLocation, String category) {
        return mapLocation.getAmenity() != null && mapLocation.getAmenity().equalsIgnoreCase(category);
    }
}
