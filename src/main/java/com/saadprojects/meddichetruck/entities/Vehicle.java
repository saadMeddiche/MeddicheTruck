package com.saadprojects.meddichetruck.entities;

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

    @OneToMany( cascade = CascadeType.ALL , fetch = FetchType.EAGER)
    private List<VehicleImage> vehicleImages;

}
