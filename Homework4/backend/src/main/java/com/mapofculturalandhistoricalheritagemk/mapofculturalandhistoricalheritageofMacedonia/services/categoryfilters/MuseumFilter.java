package com.mapofculturalandhistoricalheritagemk.mapofculturalandhistoricalheritageofMacedonia.services.categoryfilters;

import com.mapofculturalandhistoricalheritagemk.mapofculturalandhistoricalheritageofMacedonia.models.MapLocation;

public class MuseumFilter implements CategoryFilter{
    @Override
    public boolean filter(MapLocation mapLocation, String category) {
        return mapLocation.getMuseum() != null && mapLocation.getMuseum().equalsIgnoreCase(category);
    }
}
