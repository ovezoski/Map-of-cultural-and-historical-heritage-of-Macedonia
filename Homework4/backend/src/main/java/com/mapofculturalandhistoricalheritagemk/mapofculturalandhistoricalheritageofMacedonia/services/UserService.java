package com.mapofculturalandhistoricalheritagemk.mapofculturalandhistoricalheritageofMacedonia.services;

import com.mapofculturalandhistoricalheritagemk.mapofculturalandhistoricalheritageofMacedonia.models.ApplicationUser;
import org.springframework.security.core.userdetails.User;

public interface UserService {
    ApplicationUser save(ApplicationUser u);
    ApplicationUser findByUsername(String username);
}
