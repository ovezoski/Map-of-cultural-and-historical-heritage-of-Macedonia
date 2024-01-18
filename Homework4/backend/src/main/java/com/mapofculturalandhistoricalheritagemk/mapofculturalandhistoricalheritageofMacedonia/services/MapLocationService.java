package com.mapofculturalandhistoricalheritagemk.mapofculturalandhistoricalheritageofMacedonia.services;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mapofculturalandhistoricalheritagemk.mapofculturalandhistoricalheritageofMacedonia.models.MapLocation;
import com.mapofculturalandhistoricalheritagemk.mapofculturalandhistoricalheritageofMacedonia.repository.MapLocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class MapLocationService {
    private final MapLocationRepository mapLocationRepository;

    public static Double haversine(double lat1, double lon1, double lat2, double lon2) {
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
//    public Page<MapLocation> findAll(Pageable pageable, String latitude, String longitude) {
//        return sort(mapLocationRepository.findAll(), pageable, latitude, longitude);
//    }
    private Page<MapLocation> sort(List<MapLocation> list, Pageable pageable, String latitude, String longitude){
        double lat1; //User location
        double lon1;
        if (!latitude.isEmpty()) lat1 = Double.parseDouble(latitude);
        else {
            lat1 = 41.9981; //41.9785144
        }
        if(!longitude.isEmpty())
            lon1 = Double.parseDouble(longitude);
        else {
            lon1 = 21.4254; //21.4774776
        }
        Comparator<MapLocation> comparator= (o1, o2) -> Math.round(haversine(lat1, lon1, Double.parseDouble(o1.getLatitude()), Double.parseDouble(o1.getLongitude()))
                .compareTo(haversine(lat1, lon1, Double.parseDouble(o2.getLatitude()), Double.parseDouble(o2.getLongitude()))));
        list = list.stream().sorted(comparator).collect(Collectors.toList());
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), list.size());
        return new PageImpl<>(list.subList(start, end), pageable, list.size());
    }

    public Page<MapLocation> searchBy(String name, String category, String city, Pageable pageable, String latitude, String longitude) {
        List<MapLocation> list = new ArrayList<>();
//        if(!name.isEmpty()) {
//            list.addAll(mapLocationRepository
//                    .findMapLocationByNameStartingWithIgnoreCaseOrEnNameStartingWithIgnoreCase(name, name));
//        }
//        else {
//           list = mapLocationRepository.findAll();
//        }
        list = mapLocationRepository.findAll();
        String[] name_split_arr = name.split("\\s+");
        List<String> name_split_list = List.of(name_split_arr);
        if(!name.isEmpty()) {
            list = list.stream().filter(ml ->
                    Stream.of(ml.getName().split("\\s+")).anyMatch(mlName->
                            name_split_list.stream().anyMatch(nameArg->mlName.toLowerCase().startsWith(nameArg.toLowerCase()))))
                    .collect(Collectors.toList());
        }
        if(!city.isEmpty()) {
            list = list.stream().filter(ml -> ml.getAddrCity() != null && ml.getAddrCity().equalsIgnoreCase(city))
                    .collect(Collectors.toList());
        }
        if(!category.isEmpty() ) {
            list = list.stream().filter(ml -> {
                if(category.equalsIgnoreCase("artwork") && ml.getTourism() != null && ml.getTourism().equals("artwork")) {
                    return true;
                }
                if(category.equalsIgnoreCase("gallery") && ml.getTourism() != null && ml.getTourism().equals("gallery")) {
                    return true;
                }
                if(category.equalsIgnoreCase("attraction") && ml.getTourism() != null && ml.getTourism().equals("attraction")) {
                    return true;
                }
                if(category.equalsIgnoreCase("museum") && ml.getTourism() != null && ml.getTourism().equals("museum")) {
                    return true;
                }
                if(category.equalsIgnoreCase("memorial") && ml.getHistoric() != null && ml.getHistoric().equals("memorial")) {
                    return true;
                }
                if(category.equalsIgnoreCase("castle") && ml.getHistoric() != null && ml.getHistoric().equals("castle")) {
                    return true;
                }
                if(category.equalsIgnoreCase("monument") && ml.getHistoric() != null && ml.getHistoric().equals("monument")) {
                    return true;
                }
                if(category.equalsIgnoreCase("archaeological_site") && ml.getHistoric() != null && ml.getHistoric().equals("archaeological_site")) {
                    return true;
                }
                if(category.equalsIgnoreCase("locomotive") && ml.getHistoric() != null && ml.getHistoric().equals("locomotive")) {
                    return true;
                }
                if(category.equalsIgnoreCase("ruins") && ml.getHistoric() != null && ml.getHistoric().equals("ruins")) {
                    return true;
                }
                if(category.equalsIgnoreCase("historic_church") && ml.getHistoric() != null && ml.getHistoric().equals("church")) {
                    return true;
                }
                if(category.equalsIgnoreCase("monastery") && ml.getHistoric() != null && ml.getHistoric().equals("monastery")) {
                    return true;
                }
                if(category.equalsIgnoreCase("tower") && ml.getHistoric() != null && ml.getHistoric().equals("tower")) {
                    return true;
                }
                if(category.equalsIgnoreCase("tomb") && ml.getHistoric() != null && ml.getHistoric().equals("tomb")) {
                    return true;
                }
                if(category.equalsIgnoreCase("city_gate") && ml.getHistoric() != null && ml.getHistoric().equals("city_gate")) {
                    return true;
                }
                if(category.equalsIgnoreCase("wayside_shrine") && ml.getHistoric() != null && ml.getHistoric().equals("wayside_shrine")) {
                    return true;
                }
                if(category.equalsIgnoreCase("battlefield") && ml.getHistoric() != null && ml.getHistoric().equals("battlefield")) {
                    return true;
                }
                if(category.equalsIgnoreCase("aircraft") && ml.getHistoric() != null && ml.getHistoric().equals("aircraft")) {
                    return true;
                }
                if(category.equalsIgnoreCase("history_museum") && ml.getMuseum() != null && ml.getMuseum().equals("history")) {
                    return true;
                }
                if(category.equalsIgnoreCase("local_museum") && ml.getMuseum() != null && ml.getMuseum().equals("local")) {
                    return true;
                }
                if(category.equalsIgnoreCase("art_museum") && ml.getMuseum() != null && ml.getMuseum().equals("art")) {
                    return true;
                }
                if(category.equalsIgnoreCase("bust") && ml.getMemorial() != null && ml.getMemorial().equals("bust")) {
                    return true;
                }
                if(category.equalsIgnoreCase("war_memorial") && ml.getMemorial() != null && ml.getMemorial().equals("war_memorial")) {
                    return true;
                }
                if(category.equalsIgnoreCase("statue") && ml.getMemorial() != null && ml.getMemorial().equals("statue")) {
                    return true;
                }
                if(category.equalsIgnoreCase("plaque") && ml.getMemorial() != null && ml.getMemorial().equals("plaque")) {
                    return true;
                }
                if(category.equalsIgnoreCase("christian") && ml.getReligion() != null && ml.getReligion().equals("christian")) {
                    return true;
                }
                if(category.equalsIgnoreCase("church") && ml.getBuilding() != null && ml.getBuilding().equals("church")) {
                    return true;
                }
                if(category.equalsIgnoreCase("place_of_worship") && ml.getAmenity() != null && ml.getAmenity().equals("place_of_worship")) {
                    return true;
                }
                if(category.equalsIgnoreCase("arts_centre") && ml.getAmenity() != null && ml.getAmenity().equals("arts_centre")) {
                    return true;
                }
                if(category.equalsIgnoreCase("macedonian_orthodox") && ml.getDenomination() != null && ml.getDenomination().equals("macedonian_orthodox")) {
                    return true;
                }
                if(category.equalsIgnoreCase("war_grave") && ml.getTomb() != null && ml.getTomb().equals("war_grave")) {
                    return true;
                }
                if(category.equalsIgnoreCase("tombstone") && ml.getTomb() != null && ml.getTomb().equals("tombstone")) {
                    return true;
                }
                if(category.equalsIgnoreCase("ruins_church") && ml.getRuins() != null && ml.getRuins().equals("church")) {
                    return true;
                }
                return false;
            } ).collect(Collectors.toList());
            }

        return sort(list, pageable, latitude, longitude);
    }

    public Optional<MapLocation> findById(String id) {
        return mapLocationRepository.findById(id);
    }

    public void editMapLocation(MapLocation mapLocation, String title) {
        mapLocation.setName(title);
        mapLocation.setEnName(title);
        mapLocationRepository.save(mapLocation);
    }
}
