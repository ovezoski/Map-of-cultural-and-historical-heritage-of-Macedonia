package com.mapofculturalandhistoricalheritagemk.mapofculturalandhistoricalheritageofMacedonia.services.impl;

import com.mapofculturalandhistoricalheritagemk.mapofculturalandhistoricalheritageofMacedonia.models.MapLocation;
import com.mapofculturalandhistoricalheritagemk.mapofculturalandhistoricalheritageofMacedonia.repository.MapLocationRepository;
import com.mapofculturalandhistoricalheritagemk.mapofculturalandhistoricalheritageofMacedonia.services.MapLocationService;
import com.mapofculturalandhistoricalheritagemk.mapofculturalandhistoricalheritageofMacedonia.services.filters.CategoriesFilter;
import com.mapofculturalandhistoricalheritagemk.mapofculturalandhistoricalheritageofMacedonia.services.filters.CityFilter;
import com.mapofculturalandhistoricalheritagemk.mapofculturalandhistoricalheritageofMacedonia.services.filters.NameFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class MapLocationServiceImpl implements MapLocationService {
    private final MapLocationRepository mapLocationRepository;
    public MapLocationServiceImpl(MapLocationRepository mapLocationRepository) {
        this.mapLocationRepository = mapLocationRepository;
    }

    private static Double haversine(double lat1, double lon1, double lat2, double lon2) {
        double R = 6371;  // Earth's radius in kilometers

        // Convert latitude and longitude from degrees to radians
        lat1 = Math.toRadians(lat1);
        lon1 = Math.toRadians(lon1);
        lat2 = Math.toRadians(lat2);
        lon2 = Math.toRadians(lon2);

        // Calculate differences
        double dlat = lat2 - lat1;
        double dlon = lon2 - lon1;

        // Haversine formula
        double a = Math.sin(dlat / 2) * Math.sin(dlat / 2) +
                Math.cos(lat1) * Math.cos(lat2) *
                        Math.sin(dlon / 2) * Math.sin(dlon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        // Distance in kilometers
        return R * c;
    }

    public List<MapLocation> getAllLocations() {
        return mapLocationRepository.findAll();
    }

    // Sort locations to show them by distance from the user ascending.
    private Page<MapLocation> sort(List<MapLocation> list, Pageable pageable, String latitude, String longitude){
        double lat1; //User location
        double lon1;
        if (!latitude.isEmpty()) lat1 = Double.parseDouble(latitude);
        else {
            lat1 = 41.9981; //Default since User didn't share their location
        }
        if(!longitude.isEmpty())
            lon1 = Double.parseDouble(longitude);
        else {
            lon1 = 21.4254; //Default since User didn't share their location
        }
        Comparator<MapLocation> comparator= (o1, o2) -> Math.round(haversine(lat1, lon1, Double.parseDouble(o1.getLatitude()), Double.parseDouble(o1.getLongitude()))
                .compareTo(haversine(lat1, lon1, Double.parseDouble(o2.getLatitude()), Double.parseDouble(o2.getLongitude()))));
        list = list.stream().sorted(comparator).collect(Collectors.toList());
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), list.size());
        return new PageImpl<>(list.subList(start, end), pageable, list.size());
    }

    public Page<MapLocation> searchBy(String name, String category, String city, Pageable pageable,
                                      String latitude, String longitude) {
        List<MapLocation> list = mapLocationRepository.findAll();

        //Name filter, City filter and Categories filter implemented with Strategy design pattern,
        //nested with a second Strategy filter implemented in the Categories filter itself
        if(!name.isEmpty()) {
            list = NameFilter.getInstance().filter(list, name);
        }

        if(!city.isEmpty()) {
            list = CityFilter.getInstance().filter(list, city);
        }

        if(!category.isEmpty() ) {
            list = CategoriesFilter.getInstance().filter(list, category);
        }

        return sort(list, pageable, latitude, longitude);
    }

    public Optional<MapLocation> findById(String id) {
        return mapLocationRepository.findById(id);
    }

    public void editMapLocation(String mapLocationId, String title) {
        MapLocation currLocation = this.findById(mapLocationId).orElseThrow(NoSuchElementException::new);
        currLocation.setName(title);
        currLocation.setEnName(title);
        mapLocationRepository.save(currLocation);
    }

    @Override
    public Long count() {
        return mapLocationRepository.count();
    }

    @Override
    public List<MapLocation> saveAll(List<MapLocation> mapLocations) {
        return mapLocationRepository.saveAll(mapLocations);
    }

    @Override
    public MapLocation save(MapLocation ml) {
        return mapLocationRepository.save(ml);
    }
}
