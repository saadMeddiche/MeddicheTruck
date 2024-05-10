package com.MeddicheTruck.mtmain.dtos;

import com.MeddicheTruck.mtcore.annotations.AdaptedDto;
import com.MeddicheTruck.mtcore.models.BaseEntityDto;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@AdaptedDto
public class VehicleTransactionODto extends BaseEntityDto {

    public Long id;

    private LocalDate date;

    private LocalTime time;

    private String description;

    private String type;

    private Long vehicleId;

    private String vehiclePlate;

    private Long personId;

    private String personFullName;

    private Double price;
}
