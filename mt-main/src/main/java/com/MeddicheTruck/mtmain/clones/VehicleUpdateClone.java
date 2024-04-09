package com.MeddicheTruck.mtmain.clones;

import jakarta.validation.constraints.NotNull;
import lombok.*;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VehicleUpdateClone {

    @NotNull(message = "The id of the vehicle can not be null")
    public Long id;

}
