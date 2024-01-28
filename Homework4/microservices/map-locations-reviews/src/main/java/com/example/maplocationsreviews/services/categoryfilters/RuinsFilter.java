package com.example.maplocationsreviews.services.categoryfilters;

import com.example.maplocationsreviews.models.MapLocation;
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
