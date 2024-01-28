package com.example.maplocationsreviews.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class MapLocation {
    @Id
    private String id;
    @Column(columnDefinition = "NVARCHAR(255)")
    private String name;
    @JsonProperty("name:en")
    private String enName;
    private String latitude;
    private String longitude;
    @JsonProperty("addr:city")
    @Column(columnDefinition = "NVARCHAR(255)")
    private String addrCity;
    @JsonProperty("addr:city:en")
    private String enAddrCity;
    private String tourism;
    @JsonProperty("tourism_1")
    private String oneTourism;
    @JsonProperty("opening_hours")
    private String openingHours;
    private String historic;
    private String religion;
    private String phone;
    private String building;
    private String amenity;
    private String denomination;
    private String shop;
    private String memorial;
    private String museum;
    private String attraction;
    private String tomb;
    @JsonProperty("place_of_worship")
    private String placeOfWorship;
    private String ruins;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Review> reviews;
}
