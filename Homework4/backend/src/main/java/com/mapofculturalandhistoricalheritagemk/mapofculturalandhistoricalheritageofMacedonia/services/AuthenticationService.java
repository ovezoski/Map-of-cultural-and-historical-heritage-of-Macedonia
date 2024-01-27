package com.mapofculturalandhistoricalheritagemk.mapofculturalandhistoricalheritageofMacedonia.services;

import com.mapofculturalandhistoricalheritagemk.mapofculturalandhistoricalheritageofMacedonia.models.dtos.LoginResponseDTO;

public interface AuthenticationService {
    String registerUser(String username, String password);

    LoginResponseDTO loginUser(String username, String password);
}
