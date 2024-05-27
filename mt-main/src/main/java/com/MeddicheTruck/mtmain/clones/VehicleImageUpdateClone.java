package com.MeddicheTruck.mtmain.clones;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VehicleImageUpdateClone {
    @NotNull(message = "The id of the vehicle can not be null")
    public Long id;

    @NotNull(message = "The name of the vehicle image can not be null")
    @NotBlank(message = "The name of the vehicle image can not be blank")
    public String name;
}
