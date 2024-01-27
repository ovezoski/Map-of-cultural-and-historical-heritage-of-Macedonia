package com.mapofculturalandhistoricalheritagemk.mapofculturalandhistoricalheritageofMacedonia.services;

import com.mapofculturalandhistoricalheritagemk.mapofculturalandhistoricalheritageofMacedonia.models.ApplicationUser;

import java.util.List;

public interface UserService {
    ApplicationUser save(ApplicationUser u);
    ApplicationUser findByUsername(String username);
    List<String> getRolesForUser(String username);
}
