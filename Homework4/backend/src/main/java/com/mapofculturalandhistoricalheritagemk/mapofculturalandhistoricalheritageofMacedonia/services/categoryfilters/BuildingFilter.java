package com.mapofculturalandhistoricalheritagemk.mapofculturalandhistoricalheritageofMacedonia.services.categoryfilters;

import com.mapofculturalandhistoricalheritagemk.mapofculturalandhistoricalheritageofMacedonia.models.MapLocation;

public class BuildingFilter implements CategoryFilter{
    @Override
    public boolean filter(MapLocation mapLocation, String category) {
        return mapLocation.getBuilding() != null && mapLocation.getBuilding().equalsIgnoreCase(category);
    }
}
