package com.mapofculturalandhistoricalheritagemk.mapofculturalandhistoricalheritageofMacedonia.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private float score;
    @Column(columnDefinition = "NVARCHAR(255)")
    private String description;

    @JsonIgnore
    @ManyToOne
    private MapLocation mapLocation;

    private String postedBy;
}
