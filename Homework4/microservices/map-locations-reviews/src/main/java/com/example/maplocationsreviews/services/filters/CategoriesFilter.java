package com.example.maplocationsreviews.services.filters;

import com.example.maplocationsreviews.models.MapLocation;
import com.example.maplocationsreviews.services.categoryfilters.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CategoriesFilter implements Filter{
    private static List<CategoryFilter> categoryFilters;
    private static CategoriesFilter categoriesFilter;
    private CategoriesFilter() {
        categoryFilters = new ArrayList<>(Arrays.asList(AmenityFilter.getInstance(), BuildingFilter.getInstance(),
                DenominationFilter.getInstance(), HistoricFilter.getInstance(), MemorialFilter.getInstance(),
                MuseumFilter.getInstance(), ReligionFilter.getInstance(), RuinsFilter.getInstance(), TombFilter.getInstance(),
                TourismFilter.getInstance()));
    }

    // Singleton with double-checked locking
    public static CategoriesFilter getInstance() {
        if (categoriesFilter == null) {
            synchronized (CategoriesFilter.class) {
                if (categoriesFilter == null) {
                    categoriesFilter = new CategoriesFilter();
                }
            }
        }
        return categoriesFilter;
    }


    @Override
    public List<MapLocation> filter(List<MapLocation> mapLocationList, String criteria) {
        return mapLocationList.stream().filter(ml -> categoryFilters.stream().anyMatch(filter -> filter.filter(ml, criteria)))
                .collect(Collectors.toList());
    }
}
