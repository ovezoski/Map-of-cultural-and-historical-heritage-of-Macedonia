package com.mapofculturalandhistoricalheritagemk.mapofculturalandhistoricalheritageofMacedonia.services.filters;

import com.mapofculturalandhistoricalheritagemk.mapofculturalandhistoricalheritageofMacedonia.models.MapLocation;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

public class CityFilter implements Filter{
    private CityFilter() {

    }
    private static CityFilter cityFilter;

    //Singleton with double-checked locking
    public static CityFilter getInstance() {
        if (cityFilter == null) {
            synchronized (CityFilter.class) {
                if (cityFilter == null) {
                    cityFilter = new CityFilter();
                }
            }
        }
        return cityFilter;
    }
    @Override
    public List<MapLocation> filter(List<MapLocation> mapLocationList, String criteria) {
        return mapLocationList.stream().filter(ml -> ml.getAddrCity() != null && ml.getAddrCity().equalsIgnoreCase(criteria))
                .collect(Collectors.toList());
    }
}
