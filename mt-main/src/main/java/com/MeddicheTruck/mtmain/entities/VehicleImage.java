package com.MeddicheTruck.mtmain.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.TenantId;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VehicleImage {
    @TenantId
    private String tenant;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String path;

    @ManyToOne
    @JsonIgnoreProperties("vehicleImages")
    private Vehicle vehicle;
}
