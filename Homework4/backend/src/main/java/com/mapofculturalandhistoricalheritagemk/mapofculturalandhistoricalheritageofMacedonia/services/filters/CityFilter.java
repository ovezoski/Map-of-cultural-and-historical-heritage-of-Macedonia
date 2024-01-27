package com.mapofculturalandhistoricalheritagemk.mapofculturalandhistoricalheritageofMacedonia.services.filters;

import com.mapofculturalandhistoricalheritagemk.mapofculturalandhistoricalheritageofMacedonia.models.MapLocation;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CityFilter implements Filter{
    @Override
    public List<MapLocation> filter(List<MapLocation> mapLocationList, String criteria) {
        return mapLocationList.stream().filter(ml -> ml.getAddrCity() != null && ml.getAddrCity().equalsIgnoreCase(criteria))
                .collect(Collectors.toList());
    }
}
