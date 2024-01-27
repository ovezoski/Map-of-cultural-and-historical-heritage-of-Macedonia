package com.mapofculturalandhistoricalheritagemk.mapofculturalandhistoricalheritageofMacedonia.services.categoryfilters;

import com.mapofculturalandhistoricalheritagemk.mapofculturalandhistoricalheritageofMacedonia.models.MapLocation;

public class ReligionFilter implements CategoryFilter{
    @Override
    public boolean filter(MapLocation mapLocation, String category) {
        return mapLocation.getReligion() != null && mapLocation.getReligion().equalsIgnoreCase(category);
    }
}
