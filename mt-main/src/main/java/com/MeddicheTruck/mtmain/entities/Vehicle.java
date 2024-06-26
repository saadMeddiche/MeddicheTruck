package com.MeddicheTruck.mtmain.entities;

import com.MeddicheTruck.mtcore.models.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.MeddicheTruck.mtmain.enums.EngineType;
import com.MeddicheTruck.mtmain.enums.VehicleType;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Vehicle extends BaseEntity {

    private Boolean inStock;

    @Enumerated(value = EnumType.STRING)
    private VehicleType type;

    @Enumerated(value = EnumType.STRING)
    private EngineType engineType;

    private String model;

    @Column(unique = true)
    private String plate;

    @OneToMany(mappedBy = "vehicle" , cascade = CascadeType.ALL , fetch = FetchType.LAZY)
    @JsonIgnoreProperties("vehicle")
    private List<VehicleImage> images = new ArrayList<>();

    @OneToMany(mappedBy = "vehicle" , cascade = CascadeType.ALL , fetch = FetchType.LAZY)
    @JsonIgnoreProperties("vehicle")
    private List<VehicleTransaction> transactions = new ArrayList<>();

}
