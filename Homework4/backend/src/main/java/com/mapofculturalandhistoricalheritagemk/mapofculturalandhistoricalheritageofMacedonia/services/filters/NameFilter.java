package com.mapofculturalandhistoricalheritagemk.mapofculturalandhistoricalheritageofMacedonia.services.filters;

import com.mapofculturalandhistoricalheritagemk.mapofculturalandhistoricalheritageofMacedonia.models.MapLocation;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class NameFilter implements Filter{
    @Override
    public List<MapLocation> filter(List<MapLocation> mapLocationList, String criteria) {
        String[] name_split_arr = criteria.split("\\s+");
        List<String> name_split_list = List.of(name_split_arr);
        return mapLocationList.stream().filter(ml ->
                        Stream.of(ml.getName().split("\\s+")).anyMatch(mlName->
                                name_split_list.stream().anyMatch(nameArg->mlName.toLowerCase().startsWith(nameArg.toLowerCase()))))
                .collect(Collectors.toList());
    }
}
