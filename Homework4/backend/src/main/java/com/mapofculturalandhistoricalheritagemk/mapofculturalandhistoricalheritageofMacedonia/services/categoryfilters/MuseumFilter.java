package com.mapofculturalandhistoricalheritagemk.mapofculturalandhistoricalheritageofMacedonia.services.categoryfilters;

import com.mapofculturalandhistoricalheritagemk.mapofculturalandhistoricalheritageofMacedonia.models.MapLocation;

public class MuseumFilter implements CategoryFilter{
    private MuseumFilter() {

    }
    private static MuseumFilter museumFilter;

    //Singleton with double-checked locking
    public static MuseumFilter getInstance() {
        if (museumFilter == null) {
            synchronized (MuseumFilter.class) {
                if (museumFilter == null) {
                    museumFilter = new MuseumFilter();
                }
            }
        }
        return museumFilter;
    }
    @Override
    public boolean filter(MapLocation mapLocation, String category) {
        return mapLocation.getMuseum() != null && mapLocation.getMuseum().equalsIgnoreCase(category);
    }
}
