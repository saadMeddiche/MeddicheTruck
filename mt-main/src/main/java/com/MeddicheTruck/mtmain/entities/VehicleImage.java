package com.MeddicheTruck.mtmain.entities;

import com.MeddicheTruck.mtcore.models.BaseImage;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VehicleImage extends BaseImage {

    @ManyToOne
    @JsonIgnoreProperties("images")
    private Vehicle vehicle;
}
