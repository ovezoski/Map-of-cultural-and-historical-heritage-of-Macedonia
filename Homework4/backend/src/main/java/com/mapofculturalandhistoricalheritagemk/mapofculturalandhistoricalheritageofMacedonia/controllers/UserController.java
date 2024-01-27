package com.mapofculturalandhistoricalheritagemk.mapofculturalandhistoricalheritageofMacedonia.controllers;

import com.mapofculturalandhistoricalheritagemk.mapofculturalandhistoricalheritageofMacedonia.models.ApplicationUser;
import com.mapofculturalandhistoricalheritagemk.mapofculturalandhistoricalheritageofMacedonia.models.MapLocation;
import com.mapofculturalandhistoricalheritagemk.mapofculturalandhistoricalheritageofMacedonia.repository.UserRepository;
import com.mapofculturalandhistoricalheritagemk.mapofculturalandhistoricalheritageofMacedonia.services.MapLocationService;
import com.mapofculturalandhistoricalheritagemk.mapofculturalandhistoricalheritageofMacedonia.services.UserService;
import com.mapofculturalandhistoricalheritagemk.mapofculturalandhistoricalheritageofMacedonia.services.impl.MapLocationServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
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
        ApplicationUser currUser = userService.findByUsername(authentication.getName());
        List<String> result = new ArrayList<>();
        currUser.getAuthorities().forEach(a -> result.add(a.getAuthority()));

        return result;
    }
}
