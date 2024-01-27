package com.mapofculturalandhistoricalheritagemk.mapofculturalandhistoricalheritageofMacedonia.services.categoryfilters;

import com.mapofculturalandhistoricalheritagemk.mapofculturalandhistoricalheritageofMacedonia.models.MapLocation;

public class MemorialFilter implements CategoryFilter{
    private MemorialFilter() {

    }
    private static MemorialFilter memorialFilter;

    //Singleton with double-checked locking
    public static MemorialFilter getInstance() {
        if (memorialFilter == null) {
            synchronized (MemorialFilter.class) {
                if (memorialFilter == null) {
                    memorialFilter = new MemorialFilter();
                }
            }
        }
        return memorialFilter;
    }
    @Override
    public boolean filter(MapLocation mapLocation, String category) {
        return mapLocation.getMemorial() != null && mapLocation.getMemorial().equalsIgnoreCase(category);
    }
}
