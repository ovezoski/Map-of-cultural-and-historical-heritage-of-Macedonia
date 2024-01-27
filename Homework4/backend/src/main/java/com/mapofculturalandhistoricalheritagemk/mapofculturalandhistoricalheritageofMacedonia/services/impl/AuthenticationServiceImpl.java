package com.mapofculturalandhistoricalheritagemk.mapofculturalandhistoricalheritageofMacedonia.services.impl;

import com.mapofculturalandhistoricalheritagemk.mapofculturalandhistoricalheritageofMacedonia.models.ApplicationUser;
import com.mapofculturalandhistoricalheritagemk.mapofculturalandhistoricalheritageofMacedonia.models.Role;
import com.mapofculturalandhistoricalheritagemk.mapofculturalandhistoricalheritageofMacedonia.models.dtos.LoginResponseDTO;
import com.mapofculturalandhistoricalheritagemk.mapofculturalandhistoricalheritageofMacedonia.services.AuthenticationService;
import com.mapofculturalandhistoricalheritagemk.mapofculturalandhistoricalheritageofMacedonia.services.RoleService;
import com.mapofculturalandhistoricalheritagemk.mapofculturalandhistoricalheritageofMacedonia.services.TokenService;
import com.mapofculturalandhistoricalheritagemk.mapofculturalandhistoricalheritageofMacedonia.services.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserService userService;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authManager;
    private final TokenService tokenService;

    public String registerUser(String username, String password) {
        String encodedPassword = passwordEncoder.encode(password);
        Role userRole = roleService.findByAuthority("USER").get();
        Set<Role> authorities = new HashSet<>();
        authorities.add(userRole);

        ApplicationUser newUser = new ApplicationUser(0, username, encodedPassword, authorities);
        userService.save(newUser);
        return "registered user successfully.";
    }

    public LoginResponseDTO loginUser(String username, String password) {
        try {
            Authentication auth = authManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            String token = tokenService.generateJwt(auth);
            return new LoginResponseDTO(token);
        } catch (AuthenticationException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
    }
}
