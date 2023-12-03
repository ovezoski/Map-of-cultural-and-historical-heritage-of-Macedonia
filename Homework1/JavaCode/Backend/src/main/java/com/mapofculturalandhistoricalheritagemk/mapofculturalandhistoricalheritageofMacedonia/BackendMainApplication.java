package com.mapofculturalandhistoricalheritagemk.mapofculturalandhistoricalheritageofMacedonia;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mapofculturalandhistoricalheritagemk.mapofculturalandhistoricalheritageofMacedonia.models.ApplicationUser;
import com.mapofculturalandhistoricalheritagemk.mapofculturalandhistoricalheritageofMacedonia.models.MapLocation;
import com.mapofculturalandhistoricalheritagemk.mapofculturalandhistoricalheritageofMacedonia.models.Role;
import com.mapofculturalandhistoricalheritagemk.mapofculturalandhistoricalheritageofMacedonia.repository.MapLocationRepository;
import com.mapofculturalandhistoricalheritagemk.mapofculturalandhistoricalheritageofMacedonia.repository.RoleRepository;
import com.mapofculturalandhistoricalheritagemk.mapofculturalandhistoricalheritageofMacedonia.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SpringBootApplication
public class BackendMainApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendMainApplication.class, args);
	}

	@Bean
	CommandLineRunner run(RoleRepository roleRepository, UserRepository userRepository, MapLocationRepository mapLocationRepository, PasswordEncoder passwordEncoder) {
		return args -> {
			if (!roleRepository.findByAuthority("ADMIN").isPresent()) {
				Role adminRole = roleRepository.save(new Role("ADMIN"));
				roleRepository.save(new Role("USER"));

				Set<Role> roles = new HashSet<>();
				roles.add(adminRole);

				ApplicationUser admin = new ApplicationUser(1, "admin", passwordEncoder.encode("adminpassword"), roles);
				userRepository.save(admin);
			}

			ObjectMapper mapper = new ObjectMapper();
			TypeReference<List<MapLocation>> typeReference = new TypeReference<List<MapLocation>>() {};
			InputStream inputStream = new FileInputStream(new File("..\\PipeAndFilter\\src\\main\\resources\\output.json"));
					//getResourceAsStream("/json/output.json");
			try {
				List<MapLocation> mapLocations = mapper.readValue(inputStream, typeReference);
				mapLocationRepository.saveAll(mapLocations);
			} catch (IOException e) {
				System.out.println("Unable to seed locations. " + e.getMessage());
			}
		};
	}
}
