package com.MeddicheTruck.mtmain.services;

import com.MeddicheTruck.mtcore.base.BaseServiceInterface;
import com.MeddicheTruck.mtmain.dtos.VehicleDto;
import com.MeddicheTruck.mtmain.entities.Vehicle;

public interface VehicleService extends BaseServiceInterface<Vehicle, VehicleDto, VehicleDto> {
    Boolean isInStock(Long id);

    Boolean isNotInStock(Long id);
}
