package com.mapofculturalandhistoricalheritagemk.mapofculturalandhistoricalheritageofMacedonia.services.categoryfilters;

import com.mapofculturalandhistoricalheritagemk.mapofculturalandhistoricalheritageofMacedonia.models.MapLocation;

public class TourismFilter implements CategoryFilter{
    private TourismFilter() {

    }
    private static TourismFilter tourismFilter;

    //Singleton with double-checked locking
    public static TourismFilter getInstance() {
        if (tourismFilter == null) {
            synchronized (TourismFilter.class) {
                if (tourismFilter == null) {
                    tourismFilter = new TourismFilter();
                }
            }
        }
        return tourismFilter;
    }
    @Override
    public boolean filter(MapLocation mapLocation, String category) {
        return mapLocation.getTourism() != null && mapLocation.getTourism().equalsIgnoreCase(category);
    }
}
