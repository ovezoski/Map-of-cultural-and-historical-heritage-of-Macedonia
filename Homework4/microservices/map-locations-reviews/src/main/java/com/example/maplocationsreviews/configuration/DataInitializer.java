package com.example.maplocationsreviews.configuration;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.maplocationsreviews.models.MapLocation;
import com.example.maplocationsreviews.services.MapLocationService;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Component
public class DataInitializer {
    private final MapLocationService mapLocationService;

    public DataInitializer(MapLocationService mapLocationService) {
        this.mapLocationService = mapLocationService;
    }

    @PostConstruct
    public void initData() {
        if (mapLocationService.count() == 0) {
            ObjectMapper mapper = new ObjectMapper();
            TypeReference<List<MapLocation>> typeReference = new TypeReference<>() {};
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("json/output.json");
            try {
                List<MapLocation> mapLocations = mapper.readValue(inputStream, typeReference);
                mapLocationService.saveAll(mapLocations);
            } catch (IOException e) {
                System.out.println("Unable to seed locations. " + e.getMessage());
            }
        }
    }
}
