package com.mapofculturalandhistoricalheritagemk.mapofculturalandhistoricalheritageofMacedonia.services.categoryfilters;

import com.mapofculturalandhistoricalheritagemk.mapofculturalandhistoricalheritageofMacedonia.models.MapLocation;
import com.mapofculturalandhistoricalheritagemk.mapofculturalandhistoricalheritageofMacedonia.services.filters.NameFilter;

public class RuinsFilter implements CategoryFilter{
    private RuinsFilter() {

    }
    private static RuinsFilter ruinsFilter;

    //Singleton with double-checked locking
    public static RuinsFilter getInstance() {
        if (ruinsFilter == null) {
            synchronized (RuinsFilter.class) {
                if (ruinsFilter == null) {
                    ruinsFilter = new RuinsFilter();
                }
            }
        }
        return ruinsFilter;
    }
    @Override
    public boolean filter(MapLocation mapLocation, String category) {
        return mapLocation.getRuins() != null && mapLocation.getRuins().equalsIgnoreCase(category);
    }
}
