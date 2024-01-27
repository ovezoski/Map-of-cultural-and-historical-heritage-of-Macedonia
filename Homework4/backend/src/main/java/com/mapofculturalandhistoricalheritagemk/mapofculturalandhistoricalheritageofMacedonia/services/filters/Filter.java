package com.mapofculturalandhistoricalheritagemk.mapofculturalandhistoricalheritageofMacedonia.services.filters;

import com.mapofculturalandhistoricalheritagemk.mapofculturalandhistoricalheritageofMacedonia.models.MapLocation;

import java.util.List;

public interface Filter {
    List<MapLocation> filter(List<MapLocation> mapLocationList, String criteria);
}
