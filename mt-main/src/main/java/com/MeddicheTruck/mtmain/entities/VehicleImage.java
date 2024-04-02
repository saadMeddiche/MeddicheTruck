package com.MeddicheTruck.mtmain.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.TenantId;
import org.springframework.data.rest.core.annotation.RestResource;

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

    @Transient
    private byte[] photoInBase64Format;

    private String photoPath;

    @ManyToOne
    @JsonIgnoreProperties("images")
//    @RestResource(exported = false)
    private Vehicle vehicle;
}
