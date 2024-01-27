package com.mapofculturalandhistoricalheritagemk.mapofculturalandhistoricalheritageofMacedonia.configuration;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mapofculturalandhistoricalheritagemk.mapofculturalandhistoricalheritageofMacedonia.models.ApplicationUser;
import com.mapofculturalandhistoricalheritagemk.mapofculturalandhistoricalheritageofMacedonia.models.MapLocation;
import com.mapofculturalandhistoricalheritagemk.mapofculturalandhistoricalheritageofMacedonia.models.Role;
import com.mapofculturalandhistoricalheritagemk.mapofculturalandhistoricalheritageofMacedonia.services.MapLocationService;
import com.mapofculturalandhistoricalheritagemk.mapofculturalandhistoricalheritageofMacedonia.services.RoleService;
import com.mapofculturalandhistoricalheritagemk.mapofculturalandhistoricalheritageofMacedonia.services.UserService;
import jakarta.annotation.PostConstruct;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class DataInitializer {
    private final RoleService roleService;

    private final UserService userService;

    private final MapLocationService mapLocationService;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(RoleService roleService, UserService userService, MapLocationService mapLocationService, PasswordEncoder passwordEncoder) {
        this.roleService = roleService;
        this.userService = userService;
        this.mapLocationService = mapLocationService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void initData() {
        if (roleService.findByAuthority("ADMIN").isEmpty()) {
            Role adminRole = roleService.save(new Role("ADMIN"));
            roleService.save(new Role("USER"));

            Set<Role> roles = new HashSet<>();
            roles.add(adminRole);

            ApplicationUser admin = new ApplicationUser(1, "admin", passwordEncoder.encode("adminpassword"), roles);
            userService.save(admin);
        }
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
