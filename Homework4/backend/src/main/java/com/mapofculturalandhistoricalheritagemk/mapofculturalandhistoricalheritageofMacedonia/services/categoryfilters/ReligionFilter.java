package com.mapofculturalandhistoricalheritagemk.mapofculturalandhistoricalheritageofMacedonia.services.categoryfilters;

import com.mapofculturalandhistoricalheritagemk.mapofculturalandhistoricalheritageofMacedonia.models.MapLocation;

public class ReligionFilter implements CategoryFilter{
    private ReligionFilter() {

    }
    private static ReligionFilter religionFilter;

    //Singleton with double-checked locking
    public static ReligionFilter getInstance() {
        if (religionFilter == null) {
            synchronized (ReligionFilter.class) {
                if (religionFilter == null) {
                    religionFilter = new ReligionFilter();
                }
            }
        }
        return religionFilter;
    }
    @Override
    public boolean filter(MapLocation mapLocation, String category) {
        return mapLocation.getReligion() != null && mapLocation.getReligion().equalsIgnoreCase(category);
    }
}
