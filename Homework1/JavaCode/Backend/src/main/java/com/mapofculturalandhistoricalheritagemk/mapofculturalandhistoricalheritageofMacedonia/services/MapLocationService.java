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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MapLocationService {
    private final MapLocationRepository mapLocationRepository;

    public List<MapLocation> getAllLocations() {
        return mapLocationRepository.findAll();
    }

    public Page<MapLocation> searchBy(String searchTerm, String category, String city, Pageable pageable) {
        List<MapLocation> list = new ArrayList<>();
        if(!searchTerm.equals("undefined")) {
            list.addAll(mapLocationRepository
                    .findMapLocationByNameStartingWithIgnoreCaseOrEnNameStartingWithIgnoreCase(searchTerm, searchTerm));
        }
        else {
           list = mapLocationRepository.findAll();
        }
        if(!city.equals("undefined")) {
            list = list.stream().filter(ml -> ml.getAddrCity() != null && ml.getAddrCity().equalsIgnoreCase(city))
                    .collect(Collectors.toList());
        }
        if(!category.equals("undefined")) {
            list = list.stream().filter(ml -> {
                if(category.equalsIgnoreCase("historic") && ml.getHistoric() != null) {
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

        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), list.size());
        Page<MapLocation> page = new PageImpl<>(list.subList(start, end), pageable, list.size());

        return page;
    }
}
