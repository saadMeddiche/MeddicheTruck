package com.MeddicheTruck.mtmain.dtos;

import com.MeddicheTruck.mtcore.annotations.AdaptedDto;
import com.MeddicheTruck.mtcore.annotations.EnumValue;
import com.MeddicheTruck.mtcore.annotations.IncludeOnAllRequests;
import com.MeddicheTruck.mtcore.annotations.IncludeOnPutRequest;
import com.MeddicheTruck.mtcore.models.BaseEntityDto;
import com.MeddicheTruck.mtmain.enums.EngineType;
import com.MeddicheTruck.mtmain.enums.VehicleType;
import jakarta.validation.constraints.NotNull;
import lombok.*;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@AdaptedDto
public class VehicleDto extends BaseEntityDto {

    @IncludeOnPutRequest
    protected Long id;

    @EnumValue(enumClass = VehicleType.class , message = "The type of the vehicle is invalid")
    @IncludeOnAllRequests
    private VehicleType type;


    @EnumValue(enumClass = EngineType.class ,message = "The engine type of the vehicle is invalid")
    @IncludeOnAllRequests
    private EngineType engineType;

    @IncludeOnAllRequests
    @NotNull(message = "The model of the vehicle can not be null")
    private String model;

    @IncludeOnAllRequests
    @NotNull(message = "The plate of the vehicle can not be null")
    private String plate;
}
