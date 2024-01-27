package com.mapofculturalandhistoricalheritagemk.mapofculturalandhistoricalheritageofMacedonia.services.categoryfilters;

import com.mapofculturalandhistoricalheritagemk.mapofculturalandhistoricalheritageofMacedonia.models.MapLocation;

public class BuildingFilter implements CategoryFilter{
    private BuildingFilter() {

    }
    private static BuildingFilter buildingFilter;

    //Singleton with double-checked locking
    public static BuildingFilter getInstance() {
        if (buildingFilter == null) {
            synchronized (BuildingFilter.class) {
                if (buildingFilter == null) {
                    buildingFilter = new BuildingFilter();
                }
            }
        }
        return buildingFilter;
    }
    @Override
    public boolean filter(MapLocation mapLocation, String category) {
        return mapLocation.getBuilding() != null && mapLocation.getBuilding().equalsIgnoreCase(category);
    }
}
