package com.mapofculturalandhistoricalheritagemk.mapofculturalandhistoricalheritageofMacedonia.services;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mapofculturalandhistoricalheritagemk.mapofculturalandhistoricalheritageofMacedonia.models.MapLocation;
import com.mapofculturalandhistoricalheritagemk.mapofculturalandhistoricalheritageofMacedonia.models.Review;
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
    public Page<MapLocation> findAll(Pageable pageable) {
        return sort(mapLocationRepository.findAll(), pageable);
    }
    private Page<MapLocation> sort(List<MapLocation> list, Pageable pageable){
        double lat1 = 41.9785144; //User location
        double lon1 = 21.4774776;
        Comparator<MapLocation> comparator= (o1, o2) -> Math.round(haversine(lat1, lon1, Double.parseDouble(o1.getLatitude()), Double.parseDouble(o1.getLongitude()))
                .compareTo(haversine(lat1, lon1, Double.parseDouble(o2.getLatitude()), Double.parseDouble(o2.getLongitude()))));

        list = list.stream().sorted(comparator).collect(Collectors.toList());
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), list.size());
        return new PageImpl<>(list.subList(start, end), pageable, list.size());
    }

    public Page<MapLocation> searchBy(String searchTerm, String category, String city, Pageable pageable) {
        List<MapLocation> list = new ArrayList<>();
        if(!searchTerm.isEmpty()) {
            list.addAll(mapLocationRepository
                    .findMapLocationByNameStartingWithIgnoreCaseOrEnNameStartingWithIgnoreCase(searchTerm, searchTerm));
        }
        else {
           list = mapLocationRepository.findAll();
        }
        if(!city.isEmpty()) {
            list = list.stream().filter(ml -> ml.getAddrCity() != null && ml.getAddrCity().equalsIgnoreCase(city))
                    .collect(Collectors.toList());
        }
        if(!category.isEmpty() ) {
            list = list.stream().filter(ml -> {
                if(category.equalsIgnoreCase("historic") && ml.getHistoric() != null ) {
                    return true;
                }
                if(category.equalsIgnoreCase("art") && ml.getShop() != null && ml.getShop().equals("art")) {
                    return true;
                }
                if(category.equalsIgnoreCase("craft") && ml.getShop() != null && ml.getShop().equals("craft")) {
                    return true;
                }
                if(category.equalsIgnoreCase("museum") && ml.getMuseum() != null) {
                    return true;
                }
                if(category.equalsIgnoreCase("artwork") && ml.getTourism() != null && ml.getTourism().equals("artwork")) {
                    return true;
                }
                if(category.equalsIgnoreCase("gallery") && ml.getTourism() != null && ml.getTourism().equals("gallery")) {
                    return true;
                }
                if(category.equalsIgnoreCase("attraction") && ml.getTourism() != null && ml.getTourism().equals("attraction")) {
                    return true;
                }
                if(category.equalsIgnoreCase("memorial") && ml.getTourism() != null && ml.getTourism().equals("memorial")) {
                    return true;
                }
                if(category.equalsIgnoreCase("museum") && ml.getTourism() != null && ml.getTourism().equals("museum")) {
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
                if(category.equalsIgnoreCase("tomb") && ml.getTomb() != null)
                    return true;
                return false;
            } ).collect(Collectors.toList());
            }

        return sort(list, pageable);
    }

    public Optional<MapLocation> findById(String id) {
        return mapLocationRepository.findById(id);
    }

    public void addReview(MapLocation mapLocation, Review newReview) {
        mapLocation.getReviews().add(newReview);
        mapLocationRepository.save(mapLocation);
    }

    public void removeReview(MapLocation mapLocation, Review reviewToDelete) {
        mapLocation.getReviews().remove(reviewToDelete);
        mapLocationRepository.save(mapLocation);
    }

    public void editMapLocation(MapLocation mapLocation, String title) {
        mapLocation.setName(title);
        mapLocation.setEnName(title);
        mapLocationRepository.save(mapLocation);
    }
}
