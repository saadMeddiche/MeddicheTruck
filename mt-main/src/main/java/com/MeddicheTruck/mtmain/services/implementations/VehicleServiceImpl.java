package com.MeddicheTruck.mtmain.services.implementations;

import com.MeddicheTruck.mtcore.base.BaseService;
import com.MeddicheTruck.mtcore.handlingExceptions.costumExceptions.AlreadyExistsException;
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
    public void saveValidation(VehicleDto vehicleDto) {
        throwExceptionIf(repository::existsVehicleByPlate , vehicleDto.getPlate() , AlreadyExistsException::new , String.format("The vehicle with plate %s already exists" , vehicleDto.getPlate()));
        globalValidation(vehicleDto);
    }

    @Override
    public void updateValidation(VehicleDto vehicleDto) {

        // if the vehicle already exists, and it is not the same vehicle that we are updating
        if(repository.existsVehicleByPlateAndIdNot(vehicleDto.getPlate() , vehicleDto.getId()))
            throw new AlreadyExistsException(String.format("The vehicle with plate %s already exists" , vehicleDto.getPlate()));

        globalValidation(vehicleDto);
    }

    @Override
    public Boolean isInStock(Long id) {
        return repository.isInStock(id);
    }

    @Override
    public Boolean isNotInStock(Long id) {
        return !isInStock(id);
    }

}
