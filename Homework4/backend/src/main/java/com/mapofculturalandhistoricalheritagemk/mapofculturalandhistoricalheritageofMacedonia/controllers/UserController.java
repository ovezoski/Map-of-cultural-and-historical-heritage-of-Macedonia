package com.mapofculturalandhistoricalheritagemk.mapofculturalandhistoricalheritageofMacedonia.controllers;

import com.mapofculturalandhistoricalheritagemk.mapofculturalandhistoricalheritageofMacedonia.models.MapLocation;
import com.mapofculturalandhistoricalheritagemk.mapofculturalandhistoricalheritageofMacedonia.services.MapLocationService;
import com.mapofculturalandhistoricalheritagemk.mapofculturalandhistoricalheritageofMacedonia.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
@RequiredArgsConstructor
public class UserController {
    private final MapLocationService mapLocationService;
    private final UserService userService;

    @GetMapping("/")
    public String hello(Authentication auth) {
        return "Hi " + auth.getName();
    }

    @GetMapping("/mapLocations")
    public List<MapLocation> getAllLocations() {
        return mapLocationService.getAllLocations();
    }

    @GetMapping("/roles")
    public List<String> getRoles(Authentication authentication) {
        return userService.getRolesForUser(authentication.getName());
    }
}
