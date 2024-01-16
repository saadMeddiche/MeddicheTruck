package com.saadprojects.meddichetruck.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.saadprojects.meddichetruck.enums.EngineType;
import com.saadprojects.meddichetruck.enums.VehicleType;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private VehicleType type;

    private EngineType engineType;

    private String model;

    private String matricule;

    @OneToMany(mappedBy = "vehicle" , cascade = CascadeType.ALL , fetch = FetchType.EAGER)
    @JsonIgnoreProperties("vehicle")
    private List<VehicleImage> vehicleImages;

}
