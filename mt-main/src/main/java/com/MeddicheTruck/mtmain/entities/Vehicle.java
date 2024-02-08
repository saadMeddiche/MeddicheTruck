package com.MeddicheTruck.mtmain.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.MeddicheTruck.mtmain.enums.EngineType;
import com.MeddicheTruck.mtmain.enums.VehicleType;
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