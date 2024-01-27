package com.mapofculturalandhistoricalheritagemk.mapofculturalandhistoricalheritageofMacedonia.services;

import com.mapofculturalandhistoricalheritagemk.mapofculturalandhistoricalheritageofMacedonia.models.Role;

import java.util.Optional;

public interface RoleService {
    Optional<Role> findByAuthority(String authority);
    Role save(Role r);
}
