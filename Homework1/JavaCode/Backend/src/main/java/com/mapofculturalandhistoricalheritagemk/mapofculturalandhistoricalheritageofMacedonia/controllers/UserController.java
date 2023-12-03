package com.mapofculturalandhistoricalheritagemk.mapofculturalandhistoricalheritageofMacedonia.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
@RequiredArgsConstructor
public class UserController {

    @GetMapping("/")
    public String hello(Authentication auth) {
        return "Hi " + auth.getName();
    }
}
