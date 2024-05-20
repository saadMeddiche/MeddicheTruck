package com.MeddicheTruck.mtmain.services.implementations;

import com.MeddicheTruck.mtcore.base.BaseService;
import com.MeddicheTruck.mtcore.handlingExceptions.costumExceptions.AlreadyExistsException;
import com.MeddicheTruck.mtmain.dtos.PersonDto;
import com.MeddicheTruck.mtmain.entities.Person;
import com.MeddicheTruck.mtmain.repositories.PersonRepository;
import com.MeddicheTruck.mtmain.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class PersonServiceImpl extends BaseService<Person, PersonDto, PersonDto, PersonRepository> implements PersonService {

    @Autowired
    public PersonServiceImpl(PersonRepository repository) {
        super(repository , Person.class , PersonDto.class , PersonDto.class);
    }

    @Override
    public void saveValidation(PersonDto personDto) {
        throwExceptionIf(repository::existsPersonByMainPhoneNumber , personDto.getMainPhoneNumber() , AlreadyExistsException::new , String.format("The person with main phone number %s already exists" , personDto.getMainPhoneNumber()));
        globalValidation(personDto);
    }

    @Override
    public void updateValidation(PersonDto personDto) {

        if(repository.existsPersonByMainPhoneNumberAndIdNot(personDto.getMainPhoneNumber() , personDto.getId()))
            throw new AlreadyExistsException(String.format("The person with main phone number %s already exists" , personDto.getMainPhoneNumber()));

        globalValidation(personDto);
    }

    @Override
    public String recordName() {
        return "person";
    }

}
