package com.mapofculturalandhistoricalheritagemk.mapofculturalandhistoricalheritageofMacedonia.services.filters;

import com.mapofculturalandhistoricalheritagemk.mapofculturalandhistoricalheritageofMacedonia.models.MapLocation;
import com.mapofculturalandhistoricalheritagemk.mapofculturalandhistoricalheritageofMacedonia.services.categoryfilters.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CategoriesFilter implements Filter{
    private static final List<CategoryFilter> categoryFilters = new ArrayList<>(Arrays.asList(new AmenityFilter(), new BuildingFilter(), new DenominationFilter(),
            new HistoricFilter(), new MemorialFilter(), new MuseumFilter(), new ReligionFilter(), new RuinsFilter(),
            new TombFilter(), new TourismFilter()));

    @Override
    public List<MapLocation> filter(List<MapLocation> mapLocationList, String criteria) {
        return mapLocationList.stream().filter(ml -> categoryFilters.stream().anyMatch(filter -> filter.filter(ml, criteria)))
                .collect(Collectors.toList());
    }
}
