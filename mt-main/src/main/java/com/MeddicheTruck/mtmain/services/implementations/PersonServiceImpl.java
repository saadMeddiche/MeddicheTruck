package com.MeddicheTruck.mtmain.services.implementations;

import com.MeddicheTruck.mtcore.base.BaseService;
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
    public String recordName() {
        return "person";
    }

}
