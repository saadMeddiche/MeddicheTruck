package com.MeddicheTruck.mtmain.services.implementations;

import com.MeddicheTruck.mtcore.base.BaseService;
import com.MeddicheTruck.mtmain.clones.VehicleUpdateClone;
import com.MeddicheTruck.mtmain.dtos.VehicleDto;
import com.MeddicheTruck.mtmain.entities.Vehicle;
import com.MeddicheTruck.mtmain.repositories.VehicleRepository;
import com.MeddicheTruck.mtmain.services.VehicleService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class VehicleServiceImpl extends BaseService<Vehicle , VehicleDto , VehicleDto , VehicleRepository > implements VehicleService {

    @Override
    public String recordName() {
        return "vehicle";
    }

    public VehicleServiceImpl(VehicleRepository vehicleRepository) {
        super(vehicleRepository , Vehicle.class , VehicleDto.class , VehicleDto.class);
    }

    @Override
    public void updateValidation(VehicleDto vehicleDto) {
        validateObjectAgainstAnotherObject(vehicleDto , VehicleUpdateClone.class);
    }

}
