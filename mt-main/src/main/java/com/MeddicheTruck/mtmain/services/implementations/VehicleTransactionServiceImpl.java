package com.MeddicheTruck.mtmain.services.implementations;

import com.MeddicheTruck.mtcore.base.BaseService;
import com.MeddicheTruck.mtcore.handlingExceptions.costumExceptions.DoNotExistException;
import com.MeddicheTruck.mtmain.dtos.VehicleTransactionIDto;
import com.MeddicheTruck.mtmain.dtos.VehicleTransactionODto;
import com.MeddicheTruck.mtmain.entities.VehicleTransaction;
import com.MeddicheTruck.mtmain.repositories.VehicleTransactionRepository;
import com.MeddicheTruck.mtmain.services.PersonService;
import com.MeddicheTruck.mtmain.services.VehicleService;
import com.MeddicheTruck.mtmain.services.VehicleTransactionService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class VehicleTransactionServiceImpl extends BaseService<VehicleTransaction, VehicleTransactionIDto, VehicleTransactionODto, VehicleTransactionRepository> implements VehicleTransactionService {

    private final VehicleService vehicleService;

    private final PersonService personService;

    @Autowired
    public VehicleTransactionServiceImpl(VehicleTransactionRepository repository, VehicleService vehicleService, PersonService personService) {
        super(repository , VehicleTransaction.class , VehicleTransactionIDto.class , VehicleTransactionODto.class);
        this.vehicleService = vehicleService;
        this.personService = personService;
    }

    @Override
    public String recordName() {
        return "vehicleTransaction";
    }

    @Override
    public ModelMapper defineMapperForDynamicSearch() {

        ModelMapper mapper = new ModelMapper();

        TypeMap<VehicleTransaction, VehicleTransactionODto> propertyMapper =
                mapper.createTypeMap(VehicleTransaction.class, VehicleTransactionODto.class);

        propertyMapper.addMappings(
                newMapper -> newMapper.map(src -> src.getVehicle().getPlate(), VehicleTransactionODto::setVehiclePlate)
        );

        propertyMapper.addMappings(
                newMapper -> newMapper.map(src -> src.getPerson().getFullName(), VehicleTransactionODto::setPersonFullName)
        );

        return mapper;

    }

    @Override
    public void globalValidation(VehicleTransactionIDto vehicleTransactionIDto) {
        throwExceptionIf(vehicleService::doesNotExistById , vehicleTransactionIDto.getVehicleId() , DoNotExistException::new , String.format("The vehicle with id %d does not exist" , vehicleTransactionIDto.getVehicleId()));
        throwExceptionIf(personService::doesNotExistById , vehicleTransactionIDto.getPersonId() , DoNotExistException::new , String.format("The person with id %d does not exist" , vehicleTransactionIDto.getPersonId()));
        validateObject(vehicleTransactionIDto);
    }

}
