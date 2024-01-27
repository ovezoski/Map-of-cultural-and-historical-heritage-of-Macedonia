package com.mapofculturalandhistoricalheritagemk.mapofculturalandhistoricalheritageofMacedonia.services.categoryfilters;

import com.mapofculturalandhistoricalheritagemk.mapofculturalandhistoricalheritageofMacedonia.models.MapLocation;

public class MemorialFilter implements CategoryFilter{
    @Override
    public boolean filter(MapLocation mapLocation, String category) {
        return mapLocation.getMemorial() != null && mapLocation.getMemorial().equalsIgnoreCase(category);
    }
}
