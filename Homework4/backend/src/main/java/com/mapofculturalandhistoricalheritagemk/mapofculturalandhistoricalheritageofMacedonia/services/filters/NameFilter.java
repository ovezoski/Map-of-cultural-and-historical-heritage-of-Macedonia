package com.mapofculturalandhistoricalheritagemk.mapofculturalandhistoricalheritageofMacedonia.services.filters;

import com.mapofculturalandhistoricalheritagemk.mapofculturalandhistoricalheritageofMacedonia.models.MapLocation;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class NameFilter implements Filter{

    private NameFilter() {

    }
    private static NameFilter nameFilter;

    //Singleton with double-checked locking
    public static NameFilter getInstance() {
        if (nameFilter == null) {
            synchronized (NameFilter.class) {
                if (nameFilter == null) {
                    nameFilter = new NameFilter();
                }
            }
        }
        return nameFilter;
    }

    @Override
    public List<MapLocation> filter(List<MapLocation> mapLocationList, String criteria) {
        String[] name_split_arr = criteria.split("\\s+");
        List<String> name_split_list = List.of(name_split_arr);
        return mapLocationList.stream().filter(ml -> ml.getName().toLowerCase().startsWith(criteria.toLowerCase()) ||
                        Stream.of(ml.getName().split("\\s+")).anyMatch(mlName->
                                name_split_list.stream().anyMatch(nameArg->mlName.toLowerCase().startsWith(nameArg.toLowerCase()))))
                .collect(Collectors.toList());
    }
}
