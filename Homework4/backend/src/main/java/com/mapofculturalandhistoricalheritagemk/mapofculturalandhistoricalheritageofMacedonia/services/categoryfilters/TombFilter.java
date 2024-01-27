package com.mapofculturalandhistoricalheritagemk.mapofculturalandhistoricalheritageofMacedonia.services.categoryfilters;

import com.mapofculturalandhistoricalheritagemk.mapofculturalandhistoricalheritageofMacedonia.models.MapLocation;

public class TombFilter implements CategoryFilter{
    @Override
    public boolean filter(MapLocation mapLocation, String category) {
        return mapLocation.getTomb() != null && mapLocation.getTomb().equalsIgnoreCase(category);
    }
}
