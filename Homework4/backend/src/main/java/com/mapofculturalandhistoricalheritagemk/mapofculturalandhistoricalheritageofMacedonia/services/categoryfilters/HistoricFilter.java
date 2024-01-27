package com.mapofculturalandhistoricalheritagemk.mapofculturalandhistoricalheritageofMacedonia.services.categoryfilters;

import com.mapofculturalandhistoricalheritagemk.mapofculturalandhistoricalheritageofMacedonia.models.MapLocation;

public class HistoricFilter implements CategoryFilter{
    private HistoricFilter() {

    }
    private static HistoricFilter historicFilter;

    //Singleton with double-checked locking
    public static HistoricFilter getInstance() {
        if (historicFilter == null) {
            synchronized (HistoricFilter.class) {
                if (historicFilter == null) {
                    historicFilter = new HistoricFilter();
                }
            }
        }
        return historicFilter;
    }
    @Override
    public boolean filter(MapLocation mapLocation, String category) {
        return mapLocation.getHistoric() != null && mapLocation.getHistoric().equalsIgnoreCase(category);
    }
}
