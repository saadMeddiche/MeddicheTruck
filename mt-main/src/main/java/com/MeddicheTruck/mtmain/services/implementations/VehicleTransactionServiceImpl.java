package com.MeddicheTruck.mtmain.services.implementations;

import com.MeddicheTruck.mtcore.base.BaseService;
import com.MeddicheTruck.mtcore.handlingExceptions.costumExceptions.DoNotExistException;
import com.MeddicheTruck.mtmain.dtos.VehicleTransactionDto;
import com.MeddicheTruck.mtmain.entities.VehicleTransaction;
import com.MeddicheTruck.mtmain.repositories.VehicleTransactionRepository;
import com.MeddicheTruck.mtmain.services.PersonService;
import com.MeddicheTruck.mtmain.services.PieceService;
import com.MeddicheTruck.mtmain.services.VehicleService;
import com.MeddicheTruck.mtmain.services.VehicleTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class VehicleTransactionServiceImpl extends BaseService<VehicleTransaction, VehicleTransactionDto, VehicleTransactionDto , VehicleTransactionRepository> implements VehicleTransactionService {

    private final VehicleService vehicleService;

    private final PersonService personService;

    @Autowired
    public VehicleTransactionServiceImpl(VehicleTransactionRepository repository, VehicleService vehicleService, PersonService personService) {
        super(repository , VehicleTransaction.class , VehicleTransactionDto.class , VehicleTransactionDto.class);
        this.vehicleService = vehicleService;
        this.personService = personService;
    }

    @Override
    public String recordName() {
        return "vehicleTransaction";
    }

    @Override
    public void globalValidation(VehicleTransactionDto vehicleTransactionDto) {
        throwExceptionIf(vehicleService::doesNotExistById , vehicleTransactionDto.getVehicleId() , DoNotExistException::new , String.format("The vehicle with id %d does not exist" , vehicleTransactionDto.getVehicleId()));
        throwExceptionIf(personService::doesNotExistById , vehicleTransactionDto.getPersonId() , DoNotExistException::new , String.format("The person with id %d does not exist" , vehicleTransactionDto.getPersonId()));
        validateObject(vehicleTransactionDto);
    }

}
